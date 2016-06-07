package webdemo;

import java.io.File;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

public class Test {
public static void main(String[] args) throws Exception {
	Configuration cfg = new Configuration();
	cfg.setDirectoryForTemplateLoading(new File("D:/Users/EX-JIZHUOLIN700/workspace/webdemo/src/main/webapp/WEB-INF"));
	cfg.setObjectWrapper(new DefaultObjectWrapper());
	Template temp = cfg.getTemplate("test.ftl");
	Map root = new HashMap();
	root.put("user", "Big joe");
	Map latest = new HashMap();
	root.put("latestProduct", latest);
	latest.put("url", "product/greenmouse.html");
	latest.put("name", "green mouse");
	Writer out = new OutputStreamWriter(System.out);
	temp.process(root, out);
	out.flush();
	
}
}
