package Lib;
/**
 * O-R Mapping 클래스 (Object-Relation)
 * Domain 클래스 
 * DMLExample - Department클래스를 만들어서 활용하기 
 * @author 이혜림
 */
public class Department {
	private int departmentId;
	private String departmentName;
	private int managerId;
	private int locationId;
	
	//디폴트 생성자 
	public Department() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Department(int departmentId, String departmentName, int managerId, int locationId) {
		super();
		this.departmentId = departmentId;
		this.departmentName = departmentName;
		this.managerId = managerId;
		this.locationId = locationId;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	@Override
	public String toString() {
		return "Department [departmentId=" + departmentId + ", departmentName=" + departmentName + ", managerId="
				+ managerId + ", locationId=" + locationId + "]";
	}
	
	
}
