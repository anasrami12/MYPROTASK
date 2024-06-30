import { Component, OnInit, QueryList, ViewChildren, ElementRef } from '@angular/core';
import { Project } from 'src/app/models/project';
import { ProjectService } from 'src/app/servicios/project/project.service';
import { Characteristic } from 'src/app/models/characteristic';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RequestService } from 'src/app/servicios/request/request.service';
import { AuthService } from 'src/app/servicios/auth/auth.service';

@Component({
  selector: 'app-projects',
  templateUrl: './projects.component.html',
  styleUrls: ['./projects.component.css']
})
export class ProjectsComponent implements OnInit {
  terminoBusqueda: string = "";
  projects: Project[] = [];
  projectsBackup: Project[] = [];
  characteristics: Characteristic[] = [];
  selectedCharacteristicIds: number[] = [];
  expandedCategories: Set<number> = new Set<number>();
  userRole:string = "";
  requestForm!: FormGroup;
  selectedProject:any= {
    idProject: 0,
    name: '',
  }

  categories = [
    { idCategory: 1, name: "Programming Language" },
    { idCategory: 2, name: "Technologies" },
    { idCategory: 3, name: "Languages" },
  ]

  constructor(private projectService: ProjectService, private formBuilder:FormBuilder, private requestService:RequestService, private authService:AuthService) {
    this.requestForm = this.formBuilder.group({
      message: ['', Validators.required],
    });
   }

  ngOnInit(): void {
    this.getData();
    this.getCharacteristics();
    this.userRole = this.authService.getUserRole();
    console.log(this.userRole)
  }

  getData() {
    this.projectService.getData().subscribe((data: Project[]) => {
      this.projects = data;
      this.projectsBackup = [...data];
    });
  }

  getCharacteristics() {
    this.projectService.getAllCharacteristics().subscribe((data: Characteristic[]) => {
      this.characteristics = data;
    });
  }

  getFilteredCharacteristics(catId: number): Characteristic[] {
    const filtered = this.characteristics.filter(c => c.category.idCategory === catId);
    if (this.isCategoryExpanded(catId)) {
      return filtered;
    }
    return filtered.slice(0, 3);
  }

  search(): void {
    const searchTerm = this.terminoBusqueda.trim().toLowerCase();

    if (!searchTerm) {
      this.projects = [...this.projectsBackup];
      return;
    }

    this.projects = this.projectsBackup.filter(project =>
      project.name.toLowerCase().includes(searchTerm)
    );
  }

  onChangeCharacteristic(characteristicId: number, event: any) {
    if (event.target.checked) {
      this.selectedCharacteristicIds.push(characteristicId);
    } else {
      const index = this.selectedCharacteristicIds.indexOf(characteristicId);
      if (index !== -1) {
        this.selectedCharacteristicIds.splice(index, 1);
      }
    }
  }

  applySelectedCharacteristics() {
    if (this.selectedCharacteristicIds.length > 0) {
      this.projectService.getProjectsByCharacteristics(this.selectedCharacteristicIds)
        .subscribe((data: Project[]) => {
          this.projects = data;
        });
    } else {
      this.projects = [...this.projectsBackup];
    }
  }

  isCategoryExpanded(categoryId: number): boolean {
    return this.expandedCategories.has(categoryId);
  }

  toggleCategoryExpansion(categoryId: number): void {
    if (this.expandedCategories.has(categoryId)) {
      this.expandedCategories.delete(categoryId);
    } else {
      this.expandedCategories.add(categoryId);
    }
  }

  @ViewChildren('checkbox') checkboxes: QueryList<ElementRef> = new QueryList<ElementRef>();

  deselectAllCheckboxes() {
    this.checkboxes.forEach((checkbox) => {
      checkbox.nativeElement.checked = false;
    });
    this.selectedCharacteristicIds = [];
    this.applySelectedCharacteristics();
  }

  selectProject(idProject:number, name:string){
    this.selectedProject.idProject = idProject;
    this.selectedProject.name = name;
  }

  makeRequest(){

    const request = {
      idProject: this.selectedProject.idProject,
      message: this.requestForm.value.message
    };

    this.requestService.makeRequest(request).subscribe({
      next: (request) =>{
        console.log(request)
      },
      error: (error) =>{
        console.error(error)
      },
    })
  }

}
