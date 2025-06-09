import styles from './Slidebar.module.css';
import {Link,Form, redirect} from 'react-router-dom';
import '@fortawesome/fontawesome-free/css/all.min.css';

export function Slidebar({displayData,page}){
    const id=localStorage.getItem('Id');
    return(
        <div className={styles.sidebar}>
        {displayData && <h2>{displayData.name.toUpperCase()}</h2>}
        {!displayData && <h2>Welcome</h2>}
        <nav>
          <ul>
          <li className={page === "DashBoard" ? styles.active : ""}><Link to="/events"><i className="fas fa-tachometer-alt"></i>DashBoard</Link></li>
          <li className={page === "Profile" ? styles.active : ""}><Link to="/events/profile"><i className="fas fa-user"></i> Profile</Link></li>
          <li className={page === "AboutUs" ? styles.active : ""}><Link to="/aboutus"><i className="fas fa-info-circle"></i> About Us</Link></li>

            <Form action={`/logout/${id}`} method="post">
                <li><button className={styles.button}><i className="fas fa-sign-out-alt"></i>LogOut</button></li>
            </Form>
          </ul>
        </nav>
      </div>
    )
}

export async function logoutAction({params}){
  console.log("Logout ID:", params.id);
  const response = await fetch(`http://localhost:8081/logout/${params.id}`);

  if (!response.ok) {
    const error = new Error('An error occurred while updating the event');
    error.code = response.status;
    error.info = await response.json();
    throw error;
  }

  localStorage.clear();
  return redirect('/');
  
}