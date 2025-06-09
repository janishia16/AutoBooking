import React, { useState ,useEffect} from 'react';
import {useActionData ,useNavigation,useNavigate} from 'react-router-dom';
import classes from './static/UpdateProfile.module.css';
import { Slidebar } from "../UI/Slidebar";
import { FaEye, FaEyeSlash } from "react-icons/fa"; 
import { RxEyeClosed,RxEyeOpen } from "react-icons/rx";
import { useQuery } from '@tanstack/react-query';
import { fetchData,updateProfile } from '../util/http';
import { Success } from "../pages/Success";
import { useMutation } from '@tanstack/react-query';
import Modal from '../UI/Modal';

export function UpdateProfile() {
    const actionData = useActionData();
    const navigation = useNavigation();
    const navigate = useNavigate();

    const [errors, setErrors] = useState({});
    const [showPassword, setShowPassword] = useState(false);
    const [isEditing, setIsEditing] = useState(false);
    const[isModalOpen,setIsModalOpen]=useState(false);
    const[successMessage,setSuccessMessage]=useState('');

    const isSubmitting = navigation.state === 'submitting';


    const id=localStorage.getItem('Id');
    const isDriver = localStorage.getItem('User') === 'Driver';


    //Fetch user data
    const {  data, isPending, isError} = useQuery({
        queryKey:['userData'],
        queryFn: () => fetchData({id}),
    });
    
    // Mutation for updating profile
    const {mutate} = useMutation({
        mutationKey:['profile'],
        mutationFn:updateProfile,
        onSuccess: (message) => {
            setSuccessMessage(message);
            setIsEditing(false)
            setIsModalOpen(true);
        },
        onError: (error) => {
            console.error("Update failed!", error);
        }
    })

    //Initialize state for form data
    const [formData, setFormData] = useState({});


    //Update formData when data is available
    useEffect(() => {
        if (data) {
            setFormData({
                name: data.name ?? "",
                phone: data.phone ?? "",
                gender: data.gender ?? "",
                password: data.password ?? "",
                confirmpassword: data.password ?? "",
                email: data.email ?? ""
            });

            if (isDriver) {
                setFormData((prev) => ({
                    ...prev,
                    license_no : data.license_no ?? "",
                    autono : data.autono ?? ""
            }))
                
            }
        }
    }, [data]);


    const togglePasswordVisibility = () => {
        setShowPassword(showPassword => !showPassword);
    };

    const handleInputChange = (event) => {
        const { name, value } = event.target;
        setFormData({ ...formData, [name]: value });

        if(event.target.name ==='password'){
            if(event.target.value.length < 6 )
                setErrors((prev) => ({ ...prev, password: "Passwords must be 6 letter long" }));
            else
                setErrors((prev) => ({ ...prev, password: " " }));
        }

    };

    const handleEditToggle = () => {
        setIsEditing(isEditing => !isEditing);
    };


    //Validate form inputs
    const validateForm = () => {
        let tempErrors = {};
        if (!formData.name) tempErrors.name = "Name is required.";
        if (!formData.phone.match(/^\d{10}$/)) tempErrors.phone = "Phone must be 10 digits.";
        if (!formData.gender) tempErrors.gender = "Please select a gender.";

        setErrors(tempErrors);
        return Object.keys(tempErrors).length === 0;
    };

     // Handle form submission
    const handleSubmit = (event) => {
        event.preventDefault();
        if (validateForm()) {
            console.log(formData)
            mutate({
                event : formData, id : id
            });
        }
    };

    //Changing confirmpassword
    useEffect(() => {
        setFormData(prev => ({
            ...prev,
            confirmpassword: prev.password
        }));
    }, [formData.password]);


    //For SignUp Button
    const hasErrors = Object.values(errors).some(error => error && error.trim() !== '');

     //Show loading state while fetching data
    if (isPending) {
        return (
            <div className={classes["loading-container"]}>
                <div className={classes.loader}></div>
                <p>Loading...</p>
            </div>
        );
    }
    return (<div className={classes.flex}>
        <Slidebar page="Profile"/>
        <div className={classes.container}>
            <h2>Update Profile</h2>
            <form onSubmit={handleSubmit} className={classes.formpage}>
            {isDriver && (
            <div className={classes.flex}>
                <div className={classes.formGroup}>
                    <label htmlFor="license_no">License Number:</label>
                        <p>{data?.license_no ?? "N/A"}</p>
                </div>

                <div className={classes.formGroup}>
                    <label htmlFor="autono">Auto Number:</label>
                        <p>{data?.autono ?? "N/A"}</p>
                </div>
            </div>
            )}
                 <div className={classes.formGroup}>
                    <label htmlFor="email">Email:</label>
                        <p>{data?.email ?? "N/A"}</p>
                </div>
                

            <div className={classes.flex}>
                <div className={classes.formGroup}>
                    <label htmlFor="name">Name:</label>
                    {isEditing ? (<input type="text" id="name" name="name"  onChange={handleInputChange} 
                        defaultValue={data?.name ?? ''} required />
                    ) : (
                        <p>{data?.name ?? "N/A"}</p>
                    )}
                    {errors.name && <p className={classes.error}>{errors.name}</p>}
                </div>
                <div className={classes.formGroup}>
                    <label htmlFor="phone">Phone:</label>
                    {isEditing ? (<input type="tel" id="phone" name="phone" pattern="\d{10}"  
                        defaultValue={data?.phone ?? ''} onChange={handleInputChange} required />
                    ) : (
                        <p>{data?.phone ?? "N/A"}</p>
                    )}
                    {errors.phone && <p className={classes.error}>{errors.phone}</p>}
                </div>
            </div>
                <div className={classes.formGroup}>
                    <label htmlFor="gender">Gender:</label>
                    {isEditing ? (<select id="gender" name="gender"  onChange={handleInputChange} 
                        defaultValue={data?.gender ?? ''} required>
                        <option value="">Select Gender</option>
                        <option value="Male">Male</option>
                        <option value="Female">Female</option>
                        <option value="Other">Other</option>
                    </select> ) : (
                        <p>{data?.gender ?? "N/A"}</p>
                    )}
                </div>
               
                <div className={classes.formGroup}>
                    <label htmlFor="password">Password:</label>
                    {isEditing ? (
                        <div className="passwordContainer">
                            <input type={showPassword ? "text" : "password"}
                            id="password" 
                            name="password" 
                            defaultValue={data?.password ?? ''}
                            onChange={handleInputChange}
                            required />

                        </div>
                        
                        ) : (
                            <p style={{ display: "flex", alignItems: "center", gap: "10px" }}>
                                {showPassword ? data?.password ?? "N/A" : "********"}
                                <span type="button" onClick={togglePasswordVisibility} className="eyeButton">
                                    {showPassword ? <RxEyeClosed /> : <RxEyeOpen />}
                                </span>
                            </p>
                        
                    )}
                    {errors.password && <p className={classes.error}>{errors.password}</p>}
                </div>
                
                {actionData?.message && (
                    <p className={classes.error}>{actionData.message.message}</p>
                )}
                { isEditing && ( <button className={classes.button} disabled={isSubmitting || hasErrors}>{isSubmitting ? 'Submitting...' : 'Update'}</button>)}
                <button className={classes.button} onClick={handleEditToggle} type="button">
                    {isEditing ? "Cancel" : "Edit"}
                </button>
            </form>
        </div>
        {isModalOpen && <Modal onClose={() =>  {
                                setIsModalOpen(false);
                                window.location.reload();
        }}>
            <Success 
            title = "Profile Updated Successful!"
            message = {successMessage}
            />
                
        </Modal>}
    </div>);
}
