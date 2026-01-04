import React, { useEffect, useState } from 'react';
import { GoogleLogin, GoogleLogout } from 'react-google-login';
import env from 'react-dotenv';
import { gapi } from 'gapi-script';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

function App() {
	const [name, setName] = useState();
	const [email, setEmail] = useState();
	const [profilePicture, setProfilePicture] = useState();
	const [isLoggedIn, setIsLoggedIn] = useState(false);
	
	useEffect(() => {
		function start() {
		  gapi.client.init({
			clientId: env.REACT_PUBLIC_GOOGLE_CLIENT_ID,
			scope: 'email',
		  });
		}
	
		gapi.load('client:auth2', start);
	}, []);
	
	const onSuccess = response => {
		const { profileObj: { givenName, name, email, imageUrl } } = response;

		setName(name);
		setEmail(email);
		setProfilePicture(imageUrl);
		setIsLoggedIn(true);

		toast.success(`${givenName} you're logged in!`, {
            position: toast.POSITION.TOP_CENTER
        });
	};

	const onFailure = () => {
		toast.error("An error has occurred :( please try again!", {
            position: toast.POSITION.TOP_CENTER
        });
	};

	const onLogoutSuccess = () => {
		setIsLoggedIn(false);
        toast.success("You're logged off!", {
            position: toast.POSITION.TOP_CENTER
        });
	};

	return (
		<>
			<ToastContainer />
			<div className="container">
				{!isLoggedIn ?
					<GoogleLogin
						clientId={env.REACT_PUBLIC_GOOGLE_CLIENT_ID}
						buttonText="Continue with Google"
						onSuccess={onSuccess}
						onFailure={onFailure} />
					:
					<>
						<div style={{ textAlign: "center" }}>
							<h1>User Information</h1>
							<img className="profile" src={profilePicture} alt="Profile" />
							<p>Name: {name}</p>
							<p>Email: {email}</p>
						</div>
						<GoogleLogout
							clientId={env.REACT_PUBLIC_GOOGLE_CLIENT_ID}
							onLogoutSuccess={onLogoutSuccess} />
					</>}
			</div>
		</>
	);
}

export default App;
