//NoticesList.js
import React, { useState, useEffect } from "react";
import NoticeDataService from "../services/notice-service";
import { Link } from "react-router-dom";

export default function NoticesList() {
  const [notices, setNotices] = useState([]);
  const [currentNotice, setCurrentNotice] = useState(null);
  const [currentIndex, setCurrentIndex] = useState(-1);
  const [searchSummary, setSearchSummary] = useState("");

  useEffect(() => {
    retrieveNotices();
  }, []);

  const onChangeSearchSummary = (e) => {
    const searchSummary = e.target.value;
    setSearchSummary(searchSummary);
  };

  const retrieveNotices = () => {
    NoticeDataService.getAll()
      .then((response) => {
        setNotices(response.data);
        console.log(response.data);
      })
      .catch((e) => {
        console.log(e);
      });
  };

  const refreshList = () => {
    retrieveNotices();
    setCurrentNotice(null);
    setCurrentIndex(-1);
  };

  const setActiveNotice = (notice, index) => {
    setCurrentNotice(notice);
    setCurrentIndex(index);
  };

  const removeAllNotices = () => {
    NoticeDataService.deleteAll()
      .then((response) => {
        console.log(response.data);
        refreshList();
      })
      .catch((e) => {
        console.log(e);
      });
  };

  const findByNoticeSummary = () => {
    if (searchSummary.trim() !== "") {
      NoticeDataService.findByNoticeSummary(searchSummary)
        .then((response) => {
          setNotices(response.data);
          console.log(response.data);
        })
        .catch((e) => {
          console.log(e);
        });
    } else {
      // If search input is empty, reload all notices
      retrieveNotices();
    }
  };

  return (
    <div className="list row">
      <div className="col-md-8">
        <div className="input-group mb-3">
          <input
            type="text"
            className="form-control"
            placeholder="Search by Notice Summary"
            value={searchSummary}
            onChange={onChangeSearchSummary}
          />
          <div className="input-group-append ml-5">
            <button
              className="btn btn-outline-secondary"
              type="button"
              onClick={findByNoticeSummary}
            >
              Search Notice Summary
            </button>
          </div>
        </div>
      </div>
      <div className="col-md-6">
        <h4>Notices List</h4>

        <ul className="list-group">
          {notices &&
            notices.map((notice, index) => (
              <li
                className={
                  "list-group-item " + (index === currentIndex ? "active" : "")
                }
                onClick={() => setActiveNotice(notice, index)}
                key={index}
              >
                {notice.noticeSummary}
              </li>
            ))}
        </ul>

        <button
          className="m-3 btn btn-sm bg-btn"
          onClick={removeAllNotices}
        >
          Remove All
        </button>
      </div>
      <div className="col-md-6">
        {currentNotice ? (
          <div>
            <h4>notice</h4>
            <div>
              <label>
                <strong>Summary:</strong>
              </label>{" "}
              {currentNotice.noticeSummary}
            </div>
            <div>
              <label>
                <strong>Details:</strong>
              </label>{" "}
              {currentNotice.noticeDetails}
            </div>

            {/* Check if currentNotice has an id before rendering the edit link */}
            {currentNotice.id && (
              <Link
                to={"/notices/" + currentNotice.id}
                className="badge badge-warning"
              >
                Edit
              </Link>
            )}
          </div>
        ) : (
          <div>
            <br />
            <p>Please click on a notice...</p>
          </div>
        )}
      </div>
    </div>
  );
}