import styles from '../Components/static/Bookings.module.css';

import { useNavigate,Link,useLocation} from 'react-router-dom';
import {BookForm} from './BookForm';
import Modal from '../UI/Modal';
import { queryClient } from '../util/http';
import { useMutation } from '@tanstack/react-query';
import { updateBooking } from '../util/http'; 
import { Success } from "../pages/Success";
import { useState } from 'react';

export function UpdateSeat(){
    const location = useLocation();
    const navigate = useNavigate();
    const [success,setSuccess] = useState(false);

    const bookingData = location.state; 

    const {mutate ,isPending ,isError,error} = useMutation({
        mutationFn:updateBooking,
        onSettled: (error) => {
            if(!error){
                queryClient.invalidateQueries({ queryKey: ['seatBookings']}); 
                setSuccess(true);
            }
            
        }
      })
    
      function handleSubmit(formData) {
        mutate({
            event : formData, booking_id : bookingData.booking_id
        });
      }
    
    return(
        <Modal onClose={() => navigate('../')} >
            {!success && (
                <>
                    <BookForm 
                        inputData={bookingData} 
                        onSubmit={handleSubmit}  
                        action="update"
                    >
                    </BookForm>
                </>
            )}

            {success && <>
                <Success 
                title = "Booking Updated Successful!"
                message = "Your booking has been updated successfully."
                />
            
            </>}

    </Modal>
    )
}