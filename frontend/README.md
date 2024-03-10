# Bank Duit React Frontend

This repository contains a portion of the React frontend for Bank Duit portal. In this sprint we are building a web application designed for managing notices, contact forms, and user authentication. The code segments provided here enable the mentioned functionalities and are intended to be integrated with the broader Bank Duit project.

## Overview

The React frontend includes various components and services to facilitate different aspects of the application:

1. **WithRouter.js**: A higher-order component (HOC) that provides router props to its wrapped component.
2. **AddNotice.js**: Component for adding notices. It includes a form for adding new notices and communicates with the NoticeDataService to perform CRUD operations.
3. **ContactForm.js**: Component for a contact form. It allows users to input their name, email, subject, and message, which is then sent using the ContactDataService.
4. **Footer.js**: Component for the footer section of the application. It displays contact information and social media links.
5. **Home.js**: Component for the home page. It contains links to the notices list and contact form.
6. **Login.js**: Component for the login page. It allows users to input their username and password and logs them in using AuthenticationService.
![alt text](<User Login Sequence Diagram-1.png>)
7. **RegisterUser.js**: Component for the register user page. It allows a new user to be registered in the portal.
8. **LoginDashboard.js**: Component for the dashboard after logging in. It displays a welcome message and allows users to log out.
9. **Navbar.js**: Component for the navigation bar. It contains links to different sections of the application.
10. **Notice.js**: Component for displaying and editing individual notices. It communicates with the NoticeDataService to perform CRUD operations.
11. **NoticesList.js**: Component for displaying a list of notices. It includes a search feature and allows users to select and delete notices.
12. **AuthenticationService.js**: Service for handling authentication. It interacts with the server to authenticate users and manages user sessions.
13. **ContactDataService.js**: Service for handling contact form submissions. It communicates with the server to send contact form data.
14. **http-common.js**: Configuration for Axios HTTP client.
15. **notice-service.js**: Service for interacting with the server's notice-related endpoints.
16. **user-service.js**: Service for interacting with the server's register user endpoint.
17. **App.js**: Main component that sets up routing and renders other components.
18. **index.js**: Entry point of the React application.
19. **index.html**: HTML file that serves as the entry point for the React application.

## Technologies Used

- React Router: For navigation within the application.
- Axios: For handling HTTP requests to interact with backend services.
- Bootstrap: For styling and layout of the components.
- Google Fonts: A library of free and open-source fonts provided by Google, allowing easy integration of custom fonts into web projects. 
- Font Awesome: A popular icon set and toolkit, providing scalable vector icons that can be easily customized and used for enhancing the visual appeal and functionality of web applications. 

## Architecture

![alt text](<Application Architecture.drawio-1.png>)

The architecture of this React application appears to follows a typical client-server architecture with a frontend built using React and a backend  implemented using a Springboot RESTful API server. 

1. **Frontend-Backend Separation**:
   - The frontend, built with React, handles the presentation layer and user interactions.
   - The backend, which is not shown in the provided code, consists of server-side logic and a database for storing data such as notices, user information, and contact form submissions.

2. **Component-Based Architecture**:
   - The frontend follows a component-based architecture, with each UI element encapsulated within its own component.
   - Components such as Navbar, Home, AddNotice, NoticesList, ContactForm, Login, RegisterUser, and Footer provide clear separation of concerns and reusability.

3. **RESTful API**:
   - The frontend communicates with the backend through HTTP requests to RESTful endpoints.
   - Each service (e.g., notice-service, user-service, contact-form-service) likely corresponds to a set of endpoints on the backend server for performing CRUD operations on specific resources.

4. **State Management**:
   - State management in the frontend is primarily handled using React's built-in state management features such as useState and useEffect hooks.
   - The application maintains local state for managing form data, notice lists, current notice details, search queries, and authentication status.

5. **Authentication and Authorization**:
   - Authentication is implemented using HTTP basic authentication, with the user's credentials stored in session storage.
   - The AuthenticationService handles authentication-related tasks such as generating authentication tokens and setting up Axios interceptors to attach authentication headers to outgoing requests.
   - Role-based access control is not explicitly implemented in the provided code but could be extended using the role information stored in the user session.

6. **Routing**:
   - Client-side routing is implemented using React Router, allowing the application to navigate to different components based on the URL path.
   - Protected routes are used to restrict access to certain pages based on the user's authentication status.

7. **HTTP Client**:
   - Axios is used as the HTTP client for making AJAX requests to the backend server.
   - A separate Axios instance is created with a base URL pointing to the backend server, and common headers are set for all requests.

8. **Styling**:
   - Styling is applied using a combination of bootstrap and custom CSS classes, inline styles, Google fonts and FontAwesome fonts.
   - The application follows a simple and consistent design across components.

This architecture demonstrates a clear separation of concerns between the frontend and backend, follows best practices for state management and authentication, and utilizes standard tools and libraries for building a modern web application.

## Getting Started

To get started with the Bank Duit React frontend, follow these steps:

1. Clone this repository to your local machine.
2. Navigate to the project directory.
3. Install dependencies using `npm install`.
4. Start the development server with `npm start`.

Ensure that you have the necessary backend services running and properly configured to enable full functionality of the application.

