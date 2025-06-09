import React from "react";
import {Link,useActionData,Form,useNavigate} from "react-router-dom";
import styles from "./static/Login.module.css"; 
import { useLocation } from 'react-router-dom';

export default function LoginPage() {
  const actionData = useActionData(); 
  const navigate = useNavigate(); 


    if (actionData && actionData.data) {
      localStorage.setItem("Id" , actionData.data.id);

      //Changing the user in localStorage
      if(actionData.data.id.startsWith('AT')){
        localStorage.setItem('User','Driver')
      }
      else{
        localStorage.setItem('User','User')
      }

      setTimeout(() => {
        navigate("/events");
      }, 100); 
    }
  return (
    <div className={styles.container}>
      <div className={styles.loginBox}>
        <h1 className={styles.title}>Login</h1>
        <Form method='POST' className={styles.form}>
          <div className={styles.formGroup}>
            <label htmlFor="email" className={styles.label}>Email</label>
            <input
              type="email"
              id="email"
              name="email"
              className={styles.input}
              required
            />
          </div>
          <div className={styles.formGroup}>
            <label htmlFor="password" className={styles.label}>Password</label>
            <input
              type="password"
              id="password"
              name="password"
              className={styles.input}
              required
            />
          </div>
          {actionData?.message && (
            <p className={styles.error}>{actionData.message.message}</p>
          )}
          <button type="submit" className={styles.button}>Login</button>
          <p className={styles.signupText}>
            Don't have an account? <Link to ={`/users/${localStorage.getItem('User').toLowerCase()}`} className={styles.link}>Sign up</Link>
          </p>
        </Form>
        
      </div>
    </div>
  );
}

export async function loginAction({ request }) {
  const formData = await request.formData();

  const customerData = Object.fromEntries(formData); // Convert form data to an object

  const response = await fetch('http://localhost:8081/userlogin', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(customerData),
  });

  

  if (response.status !==302) {
      
    const errorData = await response.json();
    return { success: false, message: errorData || {} };
  }
  const responseData = await response.json();
  localStorage.setItem('token',responseData.data.token)


  //User Data
  const url = responseData.data.id.trim().startsWith('AT') 
    ? `http://localhost:8080/?email=${responseData.data.email}`
    : `http://localhost:8081/?email=${responseData.data.email}`;

  const userDataResponse = await fetch(url, {
    method: 'GET',
    headers: { 'Content-Type': 'application/json' },
  });
  const getUserData = await userDataResponse.json();


  
  return { success: true, data: getUserData.data };
 
  
}

