import classes from './static/Intro.module.css';
import { useNavigate } from 'react-router-dom';


export function Intro(){
    const navigate = useNavigate();

    const navigateToSignupUser = () => {
        localStorage.setItem('User','User');
        navigate('/users/login');
    };

    const navigateToSignupDriver = () => {
        localStorage.setItem('User','Driver');
        navigate('/users/login');
    };

    return(
    <div className ={classes.container}>
        <div className={classes.card}>
            <h2 className={classes.main}>Users</h2>
            <p className={classes.main}>Choose a option</p>
            <div className={classes.buttonContainer}>
                <button className={classes.button} onClick={navigateToSignupUser}> User</button>
                <button className={classes.button} onClick={navigateToSignupDriver}>Auto Driver</button>
            </div>
        </div>
    </div>
    )
}