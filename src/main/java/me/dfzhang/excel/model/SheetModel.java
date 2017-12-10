package me.dfzhang.excel.model;

/**
 * @ClassName SheetModel
 * 
 * @Version v1.0
 * @Date 2017年12月6日 下午10:56:42
 * @Author 854154025@qq.com
 * 
 * @Description TODO
 * 
 */
public class SheetModel implements Comparable<SheetModel> {
	private String name;
	private int page;
	private int maxSize;

	public SheetModel() {
	}

	public SheetModel(String name) {
		this(name, 0, 0);
	}

	public SheetModel(String name, int page, int maxSize) {
		setName(name);
		setPage(page);
		setMaxSize(maxSize);
	}

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return page
	 */
	public int getPage() {
		return page;
	}

	/**
	 * @return maxSize
	 */
	public int getMaxSize() {
		return maxSize;
	}

	/**
	 * @param name 属性赋值 name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param page 属性赋值 page
	 */
	public void setPage(int page) {
		this.page = page;
	}

	/**
	 * @param maxSize 属性赋值 maxSize
	 */
	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}

	@Override
	public int compareTo(SheetModel o) {
		if (o == null) {
			return -1;
		}
		return page - o.page;
	}

}
