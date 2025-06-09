import styles from './static/Bookings.module.css';
import {motion} from 'framer-motion';
import img from'../Image/auto.png';
import { useMutation } from '@tanstack/react-query';
import { deleteBooking,queryClient } from '../util/http';
import { useNavigate ,Link} from 'react-router-dom';


export function BookingItem({
    booking,
    onViewDetails,
    isExpanded
}){
    const navigate = useNavigate();
    const booking_id = booking.booking_id;

     //Check the user
    const User = localStorage.getItem('Id').startsWith('AT') ? 'Driver' : 'User';

    const {mutate ,isPending ,isError,error} = useMutation({
        mutationFn:() => deleteBooking({booking_id}),
        onSettled: () => {
            queryClient.invalidateQueries({ queryKey: ['seatBookings']}); 
          },
      })


    function handleDelete() {
        mutate( );

      }
    
      function handleUpdate() {
        console.log("update");
        navigate('/events/viewBooking/update-seat', { state: booking }); 
      }

    const formattedDate = new Date(booking.date).toLocaleDateString(
        'en-US',
        {
          day: '2-digit',
          month: 'short',
          year: 'numeric',
        }
      );

      const formattedTime = (time) => {
        if (!time) return ''; 
      
        const [hours, minutes] = time.split(':'); 
        const hoursInt = parseInt(hours, 10);
      
        if (hoursInt === 12) {
          return '12:00 PM';
        }
        if (hoursInt === 13 ) {
          return '01:00 PM';
        }
      
        return `${hours}:${minutes} AM`; 
      };


   
    

    return (
       <li>
        <article className={styles["booking-item"]}>
            <header>
                <img src = {img} />
                <div className={styles["booking-item-meta"]}>
                    <div className={styles["booking-group"]}>
                        <div>
                            <h2>{formattedDate}</h2>
                            <div>{formattedTime(booking.time)}</div>
                        </div>
                        <div className={styles.seat}>Number of Seats: {booking.no_of_seats}</div>
                    </div>
                    {booking.status === "Active" && <p className={styles["booking-item-actions"]}>
                        { User==='User' && (
                            <> 
                                <button onClick = {handleDelete} >
                                    Delete
                                </button>
                                <button onClick = {handleUpdate}>
                                    Update
                                </button> 
                            </>
                        )}
                    </p>
                    }
                </div>
            </header>
            <div className={styles["booking-item-details"]}>
                <p>
                    <button onClick={onViewDetails}>
                    View Details{' '}
                    <motion.span 
                        animate={{rotate : isExpanded ? 180 : 0}}
                        className={styles["booking-item-details-icon"]} >&#9650;</motion.span>
                    </button>
                </p>

                {isExpanded && User==='User' && (
                
                    <div className={styles.driverCard}>
                        <div className={styles.driverInfo}>
                            <p><strong>Auto Number:</strong> {booking.driver.autono}</p>
                            <p><strong>Driver Name:</strong> {booking.driver.name}</p>
                            <p><strong>Contact:</strong> {booking.driver.phone}</p>
                        </div>
                    </div>
                    
                )}
                {isExpanded && User==='Driver' && (
                
                    <div className={styles.driverCard}>
                        <div className={styles.driverInfo}>
                            <p><strong>Customer Name:</strong> {booking.customername}</p>
                            <p><strong>Contact:</strong> {booking.phone}</p>
                        </div>
                    </div>
                    
                )}
            </div>
        </article>
       </li>
    )
}