
import styles from './static/DashBoard.module.css';

import { useNavigate } from "react-router-dom";


export default function EventItem({ event }) {
  const navigate = useNavigate();
  

  function handleClick(value) {
    if (value === "Book") {
      navigate('/events/book-seat'); 
    }
    else if(value === "View Details")
    {
      navigate('/events/viewBooking'); 
    }

  }


    return (
      <>
        <article className={styles["event-item"]}>
          <img src={event.img} alt={event.title} />
          <div className={styles["event-item-content"]}>
            <div>
              <h2>{event.title}</h2>
            </div>
            <p>
               <button 
               onClick={() => handleClick(event.value)}
               className={styles["event-item-button"]}> {event.value}</button>
            </p>
          </div>
        </article>

      </>
      );

}