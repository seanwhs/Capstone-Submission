// notice-service.js
import { http } from "./http-common";

class NoticeDataService {
  getAll() {
    return http.get("/notices");
  }

  get(id) {
    return http.get(`/notices/${id}`);
  }

  create(data) {
    return http.post("/notices", data);
  }

  update(id, data) {
    return http.put(`/notices/${id}`, data);
  }

  delete(id) {
    return http.delete(`/notices/${id}`);
  }

  deleteAll() {
    return http.delete("/notices");
  }

  findByNoticeSummary(noticeSummary) {
    return http.get(`/notices?noticeSummary=${noticeSummary}`);
}
}

export default new NoticeDataService();