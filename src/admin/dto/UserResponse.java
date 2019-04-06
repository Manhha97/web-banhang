package admin.dto;

import java.util.List;


import admin.model.User;

public class UserResponse {
	private Integer currentPage;
    private User user;
    private Integer pageSize;
    private List list;
    private Integer total;
	    
	    
		public UserResponse() {
			super();
		}
		public Integer getCurrentPage() {
			return currentPage;
		}
		public void setCurrentPage(Integer currentPage) {
			this.currentPage = currentPage;
		}
		public User getUser() {
			return user;
		}
		public void setUser(User user) {
			this.user = user;
		}
		public Integer getPageSize() {
			return pageSize;
		}
		public void setPageSize(Integer pageSize) {
			this.pageSize = pageSize;
		}
		public List getList() {
			return list;
		}
		public void setList(List list) {
			this.list = list;
		}
		public Integer getTotal() {
			return total;
		}
		public void setTotal(Integer total) {
			this.total = total;
		}
		@Override
		public String toString() {
			return "UserResponse {currentPage=" + currentPage + ", user=" + user + ", pageSize=" + pageSize + ", list="
					+ list + ", total=" + total + "}";
		}
		
	    
}
