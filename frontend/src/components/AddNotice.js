//AddNotice.js
import React, { useState } from "react";
import NoticeDataService from "../services/notice-service";

export default function AddNotice() {
  const [noticeSummary, setNoticeSummary] = useState("");
  const [noticeDetails, setNoticeDetails] = useState("");
  const [noticBegDt, setNoticBegDt] = useState("");
  const [noticEndDt, setNoticEndDt] = useState("");
  const [submitted, setSubmitted] = useState(false);
  const [notices, setNotices] = useState([]); // Define setNotices here

  const onChangeNoticeSummary = (e) => {
    setNoticeSummary(e.target.value);
  };

  const onChangeNoticeDetails = (e) => {
    setNoticeDetails(e.target.value);
  };

  const onChangeNoticBegDt = (e) => {
    setNoticBegDt(e.target.value);
  };

  const onChangeNoticEndDt = (e) => {
    setNoticEndDt(e.target.value);
  };

  const saveNotice = () => {
    var data = {
      noticeSummary: noticeSummary,
      noticeDetails: noticeDetails,
      noticBegDt: noticBegDt,
      noticEndDt: noticEndDt
    };

    NoticeDataService.create(data)
      .then(response => {
        setSubmitted(true);
        console.log(response.data);
        refreshList(); 
      })
      .catch(e => {
        console.log(e);
      });
  };

  const newNotice = () => {
    setNoticeSummary("");
    setNoticeDetails("");
    setSubmitted(false);
  };

  const refreshList = () => {
    NoticeDataService.getAll()
      .then(response => {
        setNotices(response.data);
      })
      .catch(error => {
        console.log(error);
      });
  };
  
  return (
    <div className="submit-form">
      {submitted ? (
        <div>
          <h4>You submitted successfully!</h4>
          <button className="btn bg-btn" onClick={newNotice}>
            Add
          </button>
        </div>
      ) : (
        <div>
          <div className="form-group">
            <label htmlFor="noticeSummary">Notice Summary</label>
            <input
              type="text"
              className="form-control"
              id="noticeSummary"
              required
              value={noticeSummary}
              onChange={onChangeNoticeSummary}
              name="noticeSummary"
            />
          </div>

          <div className="form-group">
            <label htmlFor="noticeDetails">Notice Details</label>
            <input
              type="text"
              className="form-control"
              id="noticeDetails"
              required
              value={noticeDetails}
              onChange={onChangeNoticeDetails}
              name="noticeDetails"
            />
          </div>

          <div className="form-group">
            <label htmlFor="noticBegDt">Notice Begin Date (Before Today)</label>
            <input
              type="date"
              className="form-control"
              id="noticBegDt"
              required
              value={noticBegDt}
              onChange={onChangeNoticBegDt}
              name="noticBegDt"
            />
          </div>

          <div className="form-group">
            <label htmlFor="noticEndDt">Notice End Date (After Today)</label>
            <input
              type="date"
              className="form-control"
              id="noticEndDt"
              required
              value={noticEndDt}
              onChange={onChangeNoticEndDt}
              name="noticEndDt"
            />
          </div>

          <button onClick={saveNotice} className="btn bg-btn">
            Submit
          </button>
        </div>
      )}
    </div>
  );
};
