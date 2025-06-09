import React ,{useState} from "react";
import {BookForm} from "./BookForm";
import Modal from '../UI/Modal'
import { useNavigate ,Link} from "react-router-dom";
import { useMutation } from '@tanstack/react-query';
import styles from './static/BookForm.module.css';
import card from './static/Success.module.css';
import { bookSeat,queryClient,payment } from '../util/http';
import { Success } from "../pages/Success";
import { Slidebar } from "../UI/Slidebar";
import { Payment } from "./Payment";

export default function BookSeat() {

  const navigate = useNavigate();

  const [isModalOpen,setIsModalOpen] = useState(false);

  //To Display Payment Form
  const [isPaymentForm, setIsPaymentForm] = useState(false); 

  //Save the Data in DB
  const [bookData, setBookData] = useState(null);

  //Payment Data not to lose when navigating
  const [payData, setPayData] = useState({
    upiid:'',
    phone:'',
    amount:''
  }); 


  const data = queryClient.getQueryData(['bookSeat']); 

  const autoDetails = data?.Data?.driver;



  const {mutate:bookSeatMutate ,isPending:isBookPending ,isError:isBookError,error:bookError} = useMutation({
    mutationKey:['bookSeat'],
    mutationFn:bookSeat,
    onSuccess: (data) => {
      console.log("book")
      queryClient.setQueryData(['bookSeat'], data);
    }
  })


  const {mutate:paymentMutate ,isPending: isPaymentPending ,isError: isPaymentError,error: paymentError} = useMutation({
    mutationKey:['payment'],
    mutationFn:payment,
    onSuccess: (data) => {
      setIsModalOpen(true);
      queryClient.setQueryData(['payment'], data);
    }
  })

  function handleSubmit(formData) {

    console.log(formData.BookData);
    console.log(formData.PaymentData);

    //Mutate
    bookSeatMutate(formData.BookData, {
      onSuccess: () => {
          paymentMutate(formData.PaymentData);
      }
    });
  }

  // Switch to payment form
  const handleNextPage = (data,page) => {
    if(page==="bookseat")
      setBookData(data); // Store booking form data

    setIsPaymentForm(isPaymentForm => !isPaymentForm); 
  };

  //Modal Close
  const handleCloseModal = () => {
    setIsModalOpen(false); 
    navigate('/events')
    // navigate('./')
  };

  return (
    <>
        <div className={styles.container}>
          <Slidebar />
          <div className={styles.section}>
          {!isPaymentForm ? (
              <BookForm inputData={bookData} onNextPage={handleNextPage}></BookForm>
            ) : (
              <Payment onSubmit={handleSubmit} 
                        onNextPage={handleNextPage} 
                        bookseat={bookData}         //Passing the data
                        payData={payData}
              >
              </Payment>
          )}
          </div>
        </div>

      {isModalOpen && ( <>
        <Modal onClose={handleCloseModal}>
          <Success 
            title = "Booking Successful!"
            message = "Your booking has been created successfully. You can view the details below"
          >
            <div className={card.driverCard}>
                <div className={card.driverInfo}>
                    <h3>{autoDetails.name}</h3>
                    <p><strong>Auto Number:</strong> {autoDetails.autono}</p>
                    <p><strong>Contact:</strong> {autoDetails.phone}</p>
                </div>
            </div>
          </Success>
        </Modal>
      
      </>)}
    </>
  );
 
}
