// ContactForm.js
import React, { useState } from 'react';
import contactDataService from '../services/contact-form-service';

export default function ContactForm() {
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const [subject, setSubject] = useState('');
  const [message, setMessage] = useState('');
  const [submitted, setSubmitted] = useState(false);

  const handleNameChange = event => {
    setName(event.target.value);
  };

  const handleEmailChange = event => {
    setEmail(event.target.value);
  };

  const handleSubjectChange = event => {
    setSubject(event.target.value);
  };

  const handleMessageChange = event => {
    setMessage(event.target.value);
  };

  const handleSubmit = event => {
    event.preventDefault();
    const data = {
      contactName: name,
      contactEmail: email,
      subject: subject,
      message: message
    };
    contactDataService.create(data)
      .then(response => {
        console.log(response.data);
        setSubmitted(true);
      })
      .catch(error => {
        console.error('Error:', error);
      });
  };

  return (
    <div>
      {submitted ? (
        <div>
          <h4>You submitted the form successfully!</h4>
        </div>
      ) : (
        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <label htmlFor="name">Name:</label>
            <input
              type="text"
              className="form-control"
              id="name"
              value={name}
              onChange={handleNameChange}
              required
            />
          </div>
          <div className="form-group">
            <label htmlFor="email">Email:</label>
            <input
              type="email"
              className="form-control"
              id="email"
              value={email}
              onChange={handleEmailChange}
              required
            />
          </div>
          <div className="form-group">
            <label htmlFor="email">Subject:</label>
            <input
              type="subject"
              className="form-control"
              id="subject"
              value={subject}
              onChange={handleSubjectChange}
              required
            />
          </div>
          <div className="form-group">
            <label htmlFor="message">Message:</label>
            <textarea
              className="form-control"
              id="message"
              rows="3"
              value={message}
              onChange={handleMessageChange}
              required
            ></textarea>
          </div>
          <button type="submit" className="btn bg-btn">
            Submit
          </button>
        </form>
      )}
    </div>
  );
}
