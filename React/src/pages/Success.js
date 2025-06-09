
import styles from '../Components/static/Success.module.css';
 
export function Success({title,message,children}){

    return(
        <>
            <div className={styles.success}>
                <h2>{title}</h2>
                <p>{message}</p>
                {children}
            </div>
            
        </>
    )
}