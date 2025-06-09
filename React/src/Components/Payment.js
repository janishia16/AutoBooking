import { Component, useState,useRef } from 'react';

// import ErrorBlock from '../UI/ErrorBlock.jsx';
import styles from './static/BookForm.module.css';
import formImg from '../Image/payment.png'


export  function Payment({onSubmit,payData,onNextPage,bookseat}) {

    const amount = bookseat.no_of_seats * 50
    const [errors, setErrors] = useState({});
    const [isValidUpi, setIsValidUpi] = useState(false);
    const [isAgreed, setIsAgreed] = useState(false); 

    //Save the data helps when you go previous
    const handleInputChange = (event) => {
        if(event.target.name === 'upiid'){
            const upiid = event.target.value;

            // Regex validation for UPI ID
            const isValid = upiid.endsWith("@oksbi") || upiid.endsWith("@upi");
            if (!isValid) {
                setErrors((prev) => ({ ...prev, upi: "Invalid UPI ID format. Please enter a valid UPI ID." }));
                // setUpiError("Invalid UPI ID format. Please enter a valid UPI ID.");
                setIsValidUpi(isValid);
                return;
            }
            setIsValidUpi(isValid);

            setErrors((prev) => ({ ...prev, upi: "" }));
        }
        else{
            const phone = event.target.value;
            if(phone.length !==10){
                setErrors((prev) => ({ ...prev, phone: "Phone Number should be 10 Digits" }));
                // setPhoneError("Phone Number should be 10 Digits")
                return;
            }

            setErrors((prev) => ({ ...prev, phone: "" }));
        }
        
        //Setting the id in Data
        const { name, value } = event.target;
        payData[name] = value ;
    };

  function handleSubmit(event) {
    event.preventDefault();
    const formData = new FormData(event.target);
    const payData = Object.fromEntries(formData.entries()); // Convert form data to an object
    console.log(payData.upiId)
    onSubmit({
        BookData: bookseat,
        PaymentData: {
            ...payData,
            amount : amount,
            id : localStorage.getItem('Id')
        }
    });
  }

  function handlePrevious(){
    onNextPage()
  }

  //For SignUp Button
  const hasErrors = Object.values(errors).some(error => error && error.trim() !== '');



  return (
    <div className={styles["form-container"]}>
        <form className={styles["event-form"]} onSubmit={handleSubmit}>
            <h1 className={styles.title}>Payment Details</h1><br />
            <p className="control">
                <label htmlFor="amount">Amount</label>
                <span className={styles.amount}>{new Intl.NumberFormat('en-IN', { style: 'currency', currency: 'INR' }).format(amount)}</span>
            </p>
            <div className={styles.control}>
                <label htmlFor="upiId">UPI ID</label>
                <div className={styles["input-container"]}>
                    <input
                        type="text"
                        id="upiid"
                        name="upiid"
                        defaultValue={payData?.upiId ?? ""}
                        onChange={handleInputChange}
                        required
                    />
                    {isValidUpi && <span className={styles["green-tick"]} aria-live="assertive">&#x2714;</span>}
                    
                    {!isValidUpi && errors.upi && <span className={styles["red-cross"]}>&#x274C;</span>}
                </div>
            </div>

            <p className={styles.control}>
                <label htmlFor="phone">Mobile Number</label>
                <input
                    type="text"
                    id="phone"
                    name="phone"
                    defaultValue={payData?.phone ?? ""}
                    onBlur={handleInputChange}
                    required
                />
            </p>
            {errors.phone && <p style={{ color: "red" }}>{errors.phone}</p>}
            
            
            
            <div className={styles["form-actions"]}>
            <button type="button" className={styles["submitButton"]} onClick={handlePrevious}>
                Back
            </button>
            <button type="submit" className={styles["submitButton"]} disabled={hasErrors}>
                Pay
            </button>
            </div>
        </form>
        <img src={formImg} className={styles.imag}/>
    </div>
  );
}

                                       

