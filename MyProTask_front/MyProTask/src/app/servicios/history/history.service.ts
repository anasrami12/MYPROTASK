import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService } from '../auth/auth.service';

@Injectable({
  providedIn: 'root'
})
export class HistoryService {
  private idUser:any;
  
  constructor(private http:HttpClient, private authService:AuthService) { 
    this.idUser = this.authService.getUserId();

  }

  private apiUrl = 'http://localhost:8080';
  getData() {
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${localStorage.getItem('token')}`
    });
    return this.http.get<any>(`${this.apiUrl}/api/userProject/displayUserProjectByIdUser?idUser=${this.idUser}`, {headers});
  }
}
