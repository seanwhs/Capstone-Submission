// Home.js
import { Link } from 'react-router-dom';
import noticeImage from '../images/notice-image.jpg';
import contactImage from '../images/contact-form-image.png';

export default function Home() {


  return (
    <div className="container">
      <div className="row">
        <div className="col-md-6">
          <Link to="/notices-list" className="text-decoration-none">
            <div className="card bg-card" style={{ width: '18rem' }}>
              <div className="card-header"><strong>Notices</strong></div>
              <div
                className="card-body d-flex justify-content-center align-items-center"
                style={{ height: '10rem' }}
              >
                <img
                  src={noticeImage}
                  className="card-img-top"
                  alt="just a picture to beautify"
                  style={{
                    maxWidth: '100%',
                    maxHeight: '100%',
                    objectFit: 'contain'
                  }}
                />
              </div>
            </div>
          </Link>
        </div>

        <div className="col-md-6">
          <Link to="/contact" className="text-decoration-none">
            <div className="card bg-card" style={{ width: '18rem' }}>
              <div className="card-header"><strong>Contact Us</strong></div>
              <div
                className="card-body d-flex justify-content-center align-items-center"
                style={{ height: '10rem' }}
              >
                <img
                  src={contactImage}
                  className="card-img-top"
                  alt="just a picture to beautify"
                  style={{
                    maxWidth: '100%',
                    maxHeight: '100%',
                    objectFit: 'contain'
                  }}
                />
              </div>
            </div>
          </Link>
        </div>
      </div>
    </div>
  );
}