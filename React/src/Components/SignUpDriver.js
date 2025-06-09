import React, { useState,useEffect } from 'react';
import { Form,redirect, useActionData,Link ,useNavigation} from 'react-router-dom';
import { useNavigate } from 'react-router-dom';
import {validateDriverField} from '../util/validation';
import classes from './static/SignupForm.module.css';
import Modal from '../UI/Modal';
import { Success } from "../pages/Success";

export default function SignupForm() {
    const actionData = useActionData(); 
    const navigation = useNavigation();
    const navigate = useNavigate();

    const [formData, setFormData] = useState({
        autono: "",
        license_no: "",
        password : '',
        confirmPassword : ''
    });

    
    const [isModalOpen,setIsModalOpen] = useState(false);
    const [errors, setErrors] = useState({});
    const isSubmitting = navigation.state === 'submitting';

    useEffect(() => { 
        if (actionData && Object.keys(actionData).length > 0) 
            { 
                setIsModalOpen(true); 
            } 
        }, [actionData]
    );
    

    function handleChange(event) {
        const { name, value } = event.target;
        setFormData((prev) => ({ ...prev, [name]: value }));
        const errorMessage = validateDriverField(name, value);

        setErrors((prev) => ({ ...prev, [name]: errorMessage })); 
    }

    // Handle password input changes
    function handlePassword(inputIdentifier, value) {
        setFormData((prev) => ({
            ...prev,
            [inputIdentifier]: value
        }));
    }

    // Validate passwords dynamically
    useEffect(() => {
        if (formData.password && formData.confirmPassword) {
            handlePasswordCheck();
        }
    }, [formData.password]);

    function handlePasswordCheck() {
        if(formData.password.length < 6 ){
            setErrors((prev) => ({ ...prev, password: "Passwords must be 6 letter long" }));
        }
        else if (formData.password !== formData.confirmPassword) {
            setErrors((prev) => ({ ...prev, confirmpassword: "Passwords do not match!" }));
            setErrors((prev) => ({ ...prev, password: undefined }));
        } 
        else {
            setErrors((prev) => ({ ...prev, confirmpassword: undefined }));
            setErrors((prev) => ({ ...prev, password: undefined }));
        }
    }

    //For SignUp Button
    const hasErrors = Object.values(errors).some(error => error && error.trim() !== '');

    //Modal Close
    const handleCloseModal = () => {
        setIsModalOpen(false); 
        navigate('/users/login')
    };

    return (
        <>
        <div className={classes.container}>
            <h2>Sign Up</h2>
            <Form method="post" className={classes.signupForm}>
            
                    <div className={classes.formGroup}>
                        <label htmlFor="name">Driver Name:</label>
                        <input type="text" id="name" name="name" required />
                    </div>
                    <div className={classes.formGroup}>
                        <label htmlFor="autoNo">Auto Number:</label>
                        <input
                            type="text"
                            id="autono"
                            name="autono"
                            onChange={(e) => handleChange(e)} 
                            required
                        />
                        {errors.autono && <p className={classes.error}>{errors.autono}</p>}
                    </div>
                    <div className={classes.formGroup}>
                        <label htmlFor="licenseNo">License Number(DL No.):</label>
                        <input
                            type="text"
                            id="license_no"
                            name="license_no"
                            onChange={(e) => handleChange(e)} 
                            required
                        />
                        {errors.license_no && <p className={classes.error}>{errors.license_no}</p>}
                    </div>
                    
                    <div className={classes.formGroup}>
                        <label htmlFor="phone">Phone:</label>
                        <input type="tel" id="phone" name="phone" onChange={(e) => handleChange(e)} required />
                        {errors.phone && <p className={classes.error}>{errors.phone}</p>}
                    </div>
                    
                    <div className={classes.formGroup}>
                        <label htmlFor="gender">Gender:</label>
                        <select id="gender" name="gender" required>
                            <option value="">Select Gender</option>
                            <option value="Male">Male</option>
                            <option value="Female">Female</option>
                            <option value="Other">Other</option>
                        </select>
                    </div>
               
                    
                    <div className={classes.formGroup}>
                        <label htmlFor="email">Email:</label>
                        <input type="email" id="email" name="email" required />
                    </div>
                    <div className={classes.formGroup}>
                        <label htmlFor="password">Password:</label>
                        <input type="password" 
                            id="password" 
                            name="password" 
                            onChange={(e) => handlePassword('password',e.target.value)}
                            onBlur={handlePasswordCheck}
                            required 
                        />
                        {errors.password && <p className={classes.error}>{errors.password}</p>}
                    </div>
                    <div className={classes.formGroup}>
                        <label htmlFor="confirmpassword">Confirm Password:</label>
                        <input type="password" 
                            id="confirmpassword" 
                            name="confirmpassword" 
                            onChange={(e) => handlePassword('confirmPassword',e.target.value)}
                            onBlur={handlePasswordCheck}
                            required 
                        />

                        {errors.confirmpassword && <p className={classes.error}>{errors.confirmpassword}</p>}
                    </div>

                    <button className={classes.button} disabled={isSubmitting || hasErrors}>{isSubmitting ? 'Submitting...' : 'Sign Up'}</button>
            </Form>
            {actionData?.success === false && (
                <p className={classes.error}>{actionData.message}</p>
            )}
            <p className={classes.signupText}>
                Do have an account? <Link to ='/users/login' className={classes.link}>Sign In</Link>
            </p>
        </div>

        {isModalOpen && ( <>
            <Modal onClose={handleCloseModal}>
            <Success 
                title = "SignUp Successful!"
                message = "Your Account has been created successfully. You can Login In now"
            >
            </Success>
            </Modal>
        </>)}
    </>
    );
}

export async function signUpDiverAction({ request }) {
    const formData = await request.formData();
  
    const customerData = Object.fromEntries(formData); // Convert form data to an object
  
    console.log(customerData)
    const response = await fetch('http://localhost:8080/addprofile', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(customerData),
    });
  
    console.log(response)
    if (!response.ok) {
      const errorData = await response.json();
      return { success: false, message: errorData || {} };
    }
  
    return await response.json();
}