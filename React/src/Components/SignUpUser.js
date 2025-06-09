import { Form, redirect, useActionData,Link ,useNavigation} from 'react-router-dom';
import classes from './static/SignupForm.module.css';
import {useState,useEffect} from 'react';
import { useNavigate } from 'react-router-dom';
import {validateDriverField} from '../util/validation';
import Modal from '../UI/Modal';
import { Success } from "../pages/Success";

export default function SignupForm() {
  const actionData = useActionData(); //http request
  const navigation = useNavigation();
  const navigate = useNavigate();

  const [password,setPassword]=useState({
    password : '',
    confirmPassword : ''
  })

  const [errors, setErrors] = useState({});
  const [isModalOpen,setIsModalOpen] = useState(false);

  const isSubmitting = navigation.state === 'submitting';

  useEffect(() => {
    if (actionData?.success) {
        setIsModalOpen(true);
    }
  }, [actionData]);
  
  //Phone
  function handlePhone(event) {
    const { name, value } = event.target;
    const errorMessage = validateDriverField(name, value);
    setErrors((prev) => ({ ...prev, [name]: errorMessage })); 
  }

  // Handle password input changes
  function handlePassword(inputIdentifier, value) {
      setPassword((prev) => ({
          ...prev,
          [inputIdentifier]: value
      }));
  }

  // Validate passwords dynamically
  useEffect(() => {
      if (password.password && password.confirmPassword) {
          handlePasswordCheck();
      }
  }, [password]);

  function handlePasswordCheck() {
      if(password.password.length < 6 ){
          setErrors((prev) => ({ ...prev, password: "Passwords must be 6 letter long" }));
      }
      else if (password.password !== password.confirmPassword) {
          setErrors((prev) => ({ ...prev, confirmpassword: "Passwords do not match!" }));
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
  

  return (<>
    <div className={classes.container}>
      <h2>Sign Up</h2>
      <Form method="post"  className={classes.signupForm}>
        <div className={classes.formGroup}>
          <label htmlFor="name">Name:</label>
          <input type="text" id="name" name="name" required />
          {actionData?.message?.name && (
            <p className={classes.error}>{actionData.message.name}</p>
          )}
        </div>
        <div className={classes.formGroup}>
          <label htmlFor="phone">Phone:</label>
          <input type="tel" id="phone" name="phone" pattern="\d{10}" 
                onChange={(e) => handlePhone(e)}
                required />
          {errors.phone && <p className={classes.error}>{errors.phone}</p>}
          {actionData?.message?.phone && (
            <p className={classes.error}>{actionData.message.phone}</p>
          )}
        </div>
        <div className={classes.formGroup}>
          <label htmlFor="gender">Gender:</label>
          <select id="gender" name="gender" required>
            <option value="">Select Gender</option>
            <option value="male">Male</option>
            <option value="female">Female</option>
            <option value="other">Other</option>
          </select>
          {actionData?.message?.gender && (
            <p className={classes.error}>{actionData.message.gender}</p>
          )}
        </div>
        <div className={classes.formGroup}>
          <label htmlFor="email">Email:</label>
          <input type="email" id="email" name="email" required />
          {actionData?.message?.email && (
            <p className={classes.error}>{actionData.message.email}</p>
          )}
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
          {actionData?.message?.password && (
            <p className={classes.error}>{actionData.message.password}</p>
          )}
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
          {actionData?.message?.confirmPassword && (
            <p className={classes.error}>{actionData.message.confirmPassword}</p>
          )}
          {errors.confirmpassword && <p className={classes.error}>{errors.confirmpassword}</p>}
        </div>
        {actionData?.message && (
            <p className={classes.error}>{actionData.message.message}</p>
          )}
        <button className={classes.button} disabled={isSubmitting || hasErrors}>{isSubmitting ? 'Submitting...' : 'Sign Up'}</button>
        
      </Form>
      <p className={classes.signupText}>
          Do have an account? <Link to ='/users/login' className={classes.link}>Sign In</Link>
      </p>
      {/* <li><a href="/events">Demo</a></li> */}
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


export async function signUpAction({ request }) {
    const formData = await request.formData();
  
    const customerData = Object.fromEntries(formData); // Convert form data to an object
  
    const response = await fetch('http://localhost:8081/addprofile', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(customerData),
    });
  
    if (!response.ok) {
      const errorData = await response.json();
      return { success: false, message: errorData || {} };
    }
  
    return redirect('/users/login')
}
  
