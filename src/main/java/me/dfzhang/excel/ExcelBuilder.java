package me.dfzhang.excel;

/**
 * @ClassName com.horige.excel.ExcelBuilder
 * 
 * @Version v1.0
 * @Date 2017年12月1日 下午9:56:33
 * @Author 854154025@qq.com
 * 
 * @Description TODO
 * 
 */
public class ExcelBuilder {
	
	
	// // private LinkedHashMap<String, CellData> data = new LinkedHashMap<>();
	// private Class<?> templateClass;
	// private BasicInfo baseInfo;
	// private List<SheetInfo> sheetInfos = new ArrayList<>();
	// private Map<String, ExcelHeader> headers = new HashMap<>();
	// private Editor editor;
	//
	// private ExcelBuilder() {
	// }
	//
	// public static CreatorBuilder creator(Class<?> template) {
	// return new CreatorBuilder(template);
	// }
	//
	// public static CreatorBuilder creator() {
	// return new CreatorBuilder();
	// }
	//
	// public Editor getEditor() {
	// return editor;
	// }
	//
	// public static class CreatorBuilder {
	// ExcelBuilder builder = new ExcelBuilder();
	//
	// CreatorBuilder() {
	// template(null);
	// }
	//
	// CreatorBuilder(Class<?> template) {
	// template(template);
	// }
	//
	// public CreatorBuilder template(Class<?> template) {
	// builder.templateClass = template;
	// builder.baseInfo = parseBaseInfo(builder.templateClass);
	// builder.sheetInfos.clear();
	// builder.sheetInfos.addAll(parseSheetInfo(builder.templateClass));
	// builder.headers.clear();
	// builder.headers.putAll(parseHeader(builder.templateClass));
	// return this;
	// }
	//
	// public CreatorBuilder name(String excelName) {
	// builder.baseInfo.setName(excelName);
	// return this;
	// }
	//
	// public CreatorBuilder type(FileType excelFileType) {
	// builder.baseInfo.setType(excelFileType);
	// return this;
	// }
	//
	// public CreatorBuilder header(ExcelHeader excelHeader) {
	// builder.headers.put(excelHeader.getKey(), excelHeader);
	// return this;
	// }
	//
	// public CreatorBuilder headers(Map<String, ExcelHeader> excelHeaders) {
	// Map<String, ExcelHeader> headers = builder.headers;
	// headers.clear();
	// headers.putAll(excelHeaders);
	// return this;
	// }
	//
	// public ExcelBuilder build() {
	// builder.editor = builder.new Editor();
	// return builder;
	// }
	//
	// BasicInfo parseBaseInfo(Class<?> templateClass) {
	// BasicInfo baseInfo = new BasicInfo();
	// if (templateClass == null) {
	// baseInfo.setName(System.nanoTime() + "");
	// baseInfo.setType(FileType.XLS);
	// return baseInfo;
	// }
	// Template excelTemplate = ReflectionUtils.findAnnotataion(templateClass, Template.class);
	// if (excelTemplate == null) {
	// baseInfo.setName(templateClass.getSimpleName());
	// baseInfo.setType(FileType.XLS);
	// } else {
	// baseInfo.setName(excelTemplate.name());
	// baseInfo.setType(excelTemplate.type());
	// }
	// return baseInfo;
	// }
	//
	// List<SheetInfo> parseSheetInfo(Class<?> templateClass) {
	// List<SheetInfo> sheetInfos = null;
	// if (templateClass == null) {
	// sheetInfos = new ArrayList<>(1);
	// sheetInfos.add(new SheetInfo("sheet1"));
	// return sheetInfos;
	// }
	// Template excelTemplate = ReflectionUtils.findAnnotataion(templateClass, Template.class);
	// me.dfzhang.excel.annotation.Sheet[] sheets = excelTemplate.sheet();
	// if (sheets.length > 0) {
	// sheetInfos = new ArrayList<>(sheets.length);
	// for (me.dfzhang.excel.annotation.Sheet sheet : sheets) {
	// sheetInfos.add(new SheetInfo(sheet.name(), sheet.page(), sheet.size()));
	// }
	// }
	// return sheetInfos;
	// }
	//
	// Map<String, ExcelHeader> parseHeader(Class<?> templateClass) {
	// Map<String, ExcelHeader> headers = new HashMap<>();
	// if (templateClass == null) {
	// return headers;
	// }
	// Field[] fields = templateClass.getDeclaredFields();
	// if (fields == null || fields.length < 1) {
	// return headers;
	// }
	// try {
	// PropertyDescriptor descriptor = null;
	// Method readMethod = null;
	// me.dfzhang.excel.annotation.Cell excelCell = null;
	// String filedName;
	// ExcelHeader header = null;
	// for (Field field : fields) {
	// if (Modifier.isStatic(field.getModifiers())) {
	// continue;
	// }
	// filedName = field.getName();
	// excelCell = ReflectionUtils.findAnnotataion(field, me.dfzhang.excel.annotation.Cell.class);
	// descriptor = new PropertyDescriptor(filedName, templateClass);
	// if ((readMethod = descriptor.getReadMethod()) != null) {
	// if (excelCell == null) {
	// excelCell = ReflectionUtils.findAnnotataion(readMethod, me.dfzhang.excel.annotation.Cell.class);
	// }
	// }
	// if (excelCell == null) {
	// continue;
	// }
	// header = new ExcelHeader(excelCell.column(), filedName, excelCell.header());
	// headers.put(filedName, header);
	// }
	// } catch (IntrospectionException e) {
	// e.printStackTrace();
	// }
	// return headers;
	// }
	// }
	//
	// public class Editor {
	// Workbook workbook;
	// List<Sheet> sheets = new ArrayList<>();
	// Sheet curSheet;
	// SheetInfo curSheetInfo;
	// int startRow = 0;
	//
	// Editor() {
	// if (FileType.XLS == baseInfo.getType()) {
	// workbook = new HSSFWorkbook();
	// } else {
	// workbook = new SXSSFWorkbook(1000);
	// }
	// sheets = new ArrayList<>(sheetInfos.size());
	// curSheetInfo = sheetInfos.get(0);
	// curSheet = workbook.createSheet(curSheetInfo.getName());
	//
	// if (headers.size() > 0) {
	// Row row = curSheet.createRow(startRow);
	// startRow++;
	// CellStyle cellStyle = workbook.createCellStyle();
	// cellStyle.setWrapText(true);
	// cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
	// List<ExcelHeader> excelHeaders = new ArrayList<>(headers.values());
	// resort(excelHeaders);
	//
	// Cell cell = null;
	// int i = 0;
	// for (ExcelHeader header : excelHeaders) {
	// cell = row.createCell(i++);
	// cell.setCellStyle(cellStyle);
	// cell.setCellValue(header.getValue());
	// }
	// }
	// }
	//
	// public Editor useSheet(int num) {
	// curSheet = sheets.get(num - 1);
	// startRow = curSheet.getLastRowNum();
	// return this;
	// }
	//
	// public Editor write(Collection<?> col) {
	// if (headers.size() > 0) {
	// startRow = 1;
	// }
	// return append(col);
	// }
	//
	// public Editor append(Collection<?> col) {
	// List<Map<String, String>> datas = new ArrayList<>(col.size());
	// Map<String, String> map = null;
	// Row row = null;
	// Collection<ExcelHeader> excelHeaders = headers.values();
	//
	// int maxCell = excelHeaders.size();
	// int fullStart = maxCell;
	// for (Object object : col) {
	// datas.add(map = ReflectionUtils.describe(object));
	// row = curSheet.createRow(startRow++);
	// map.remove("class");
	// for (ExcelHeader header : excelHeaders) {
	// row.createCell(header.getIndex()).setCellValue(map.get(header.getKey()));
	// map.remove(header.getKey());
	// }
	// if (!map.isEmpty()) {
	// fullStart = maxCell;
	// for (Entry<String, String> entry : map.entrySet()) {
	// row.createCell(fullStart++).setCellValue(entry.getValue());
	// }
	// }
	// }
	// return this;
	// }
	//
	// void resort(List<ExcelHeader> headers) {
	// Collections.sort(headers);
	// int i = 0;
	// for (ExcelHeader header : headers) {
	// header.setIndex(i++);
	// }
	// }
	//
	// public File asFile(String fullPath) {
	// return asFile(new File(fullPath));
	// }
	//
	// public File asFile(File file) {
	// FileOutputStream out = null;
	// try {
	// out = new FileOutputStream(file);
	// workbook.write(out);
	// out.flush();
	// } catch (IOException e) {
	// e.printStackTrace();
	// } finally {
	// try {
	// out.close();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }
	// return file;
	// }
	//
	// public File toDir(String dir) {
	// if (!dir.endsWith(File.separator)) {
	// dir = dir + File.separator;
	// }
	// File file = new File(dir + baseInfo.getName() + "." + baseInfo.getType().getValue());
	// return asFile(file);
	// }
	//
	// public void asStream(OutputStream out) {
	// try {
	// workbook.write(out);
	// out.flush();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }
	// }
	//
	// // 首字母转小写
	// public static String toLowerCaseFirstOne(String s) {
	// if (Character.isLowerCase(s.charAt(0)))
	// return s;
	// else
	// return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
	// }
	//
	// // 首字母转大写
	// public static String toUpperCaseFirstOne(String s) {
	// if (Character.isUpperCase(s.charAt(0)))
	// return s;
	// else
	// return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
	// }
	//
	// public static void main(String[] args) {
	// List<StudentTemplate> list = new ArrayList<>();
	// StudentTemplate tamplate = new StudentTemplate();
	// tamplate.setAge(11);
	// tamplate.setId(1);
	// tamplate.setName("Dave");
	// list.add(tamplate);
	//
	// tamplate = new StudentTemplate();
	// tamplate.setAge(12);
	// tamplate.setId(2);
	// tamplate.setName("Dave2");
	// list.add(tamplate);
	//
	// tamplate = new StudentTemplate();
	// tamplate.setAge(13);
	// tamplate.setId(3);
	// tamplate.setName("Dave3");
	// list.add(tamplate);
	//
	// ExcelBuilder.creator(StudentTemplate.class).build().getEditor().append(list).append(list).append(list).toDir("D:");
	//
	// }

}
