//Notice.js
import React, { useState, useEffect } from "react";
import NoticeDataService from "../services/notice-service";
import WithRouter from '../common/WithRouter';

const Notice = ({ router }) => {
  const [currentNotice, setCurrentNotice] = useState(null);
  const [message, setMessage] = useState("");

  useEffect(() => {
    getNotice(router.params.id);
  }, [router.params.id]);

  const onChangeNoticeSummary = (e) => {
    const noticeSummary = e.target.value;
    setCurrentNotice(prevState => ({
      ...prevState,
      noticeSummary: noticeSummary
    }));
  };

  const onChangeNoticeDetails = (e) => {
    const noticeDetails = e.target.value;
    setCurrentNotice(prevState => ({
      ...prevState,
      noticeDetails: noticeDetails
    }));
  };

  const onChangeNoticBegDt = (e) => {
    const noticBegDt = e.target.value;
    setCurrentNotice(prevState => ({
      ...prevState,
      noticBegDt: noticBegDt
    }));
  };

  const onChangeNoticEndDt = (e) => {
    const noticEndDt = e.target.value;
    setCurrentNotice(prevState => ({
      ...prevState,
      noticBegDt: noticEndDt
    }));
  };

  const onChangeCreateDt = (e) => {
    const createDt = e.target.value;
    setCurrentNotice(prevState => ({
      ...prevState,
      createDt: createDt
    }));
  };

  const onChangeUpdateDt = (e) => {
    const updateDt = e.target.value;
    setCurrentNotice(prevState => ({
      ...prevState,
      updateDt: updateDt
    }));
  };

  const getNotice = (id) => {
    NoticeDataService.get(id)
      .then(response => {
        setCurrentNotice(response.data);
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      });
  };

  const updateNotice = () => {
    NoticeDataService.update(
      currentNotice.id,
      currentNotice
    )
      .then(response => {
        console.log(response.data);
        setMessage("The Notice was updated successfully!");
      })
      .catch(e => {
        console.log(e);
      });
  };

  const deleteNotice = () => {    
    NoticeDataService.delete(currentNotice.id)
      .then(response => {
        console.log(response.data);
        router.navigate('/Notices');
      })
      .catch(e => {
        console.log(e);
      });
  };

  return (
    <div>
      {currentNotice ? (
        <div className="edit-form">
          <h4>Notice</h4>
          <form>
            <div className="form-group">
              <label htmlFor="noticeSummary">Notice Summary</label>
              <input
                type="text"
                className="form-control"
                id="noticeSummary"
                value={currentNotice.noticeSummary}
                onChange={onChangeNoticeSummary}
              />
            </div>
          
            <div className="form-group">
              <label htmlFor="noticeDetails">Notice Details</label>
              <input
                type="text"
                className="form-control"
                id="noticeDetails"
                value={currentNotice.noticeDetails}
                onChange={onChangeNoticeDetails}
              />
            </div>

            <div className="form-group">
              <label htmlFor="noticBegDt">Notice Begin Date</label>
              <input
                type="text"
                className="form-control"
                id="noticBegDt"
                value={currentNotice.noticBegDt}
                onChange={onChangeNoticBegDt}
              />
            </div>

            <div className="form-group">
              <label htmlFor="noticEndDt">Notice End Date</label>
              <input
                type="text"
                className="form-control"
                id="noticEndDt"
                value={currentNotice.noticEndDt}
                onChange={onChangeNoticEndDt}
              />
            </div>

            <div className="form-group">
              <label htmlFor="noticEndDt">Date Created</label>
              <input
                type="text"
                className="form-control"
                id="createDt"
                value={currentNotice.createDt}
                onChange={onChangeCreateDt}
              />
            </div>

            <div className="form-group">
              <label htmlFor="updateDt">Date Updated</label>
              <input
                type="text"
                className="form-control"
                id="updateDt"
                value={currentNotice.updateDt}
                onChange={onChangeUpdateDt}
              />
            </div>
          
          </form>

          

          <button
            className="badge badge-danger mr-2"
            onClick={deleteNotice}
          >
            Delete
          </button>

          <button
            type="submit"
            className="badge badge-success"
            onClick={updateNotice}
          >
            Update
          </button>
          <p>{message}</p>
        </div>
      ) : (
        <div>
          <br />
          <p>Please click on a Notice...</p>
        </div>
      )}
    </div>
  );
};

export default WithRouter(Notice);