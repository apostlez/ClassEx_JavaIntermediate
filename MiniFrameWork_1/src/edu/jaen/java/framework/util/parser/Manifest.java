package edu.jaen.java.framework.util.parser;

/**Manifext.xml  파일을 읽어서 Component 하나를 저장하기 위한 VO */
public class Manifest {
		/**component 종류  activity :1,  service  :2 */
		private String componentKnd;   		
		/**component  이름
		 * component 이름으로 해당 component를 수행시킨다.*/
		private String name;		
		/**프로그램 시작시 해당 component를 수행시킬지 여부를 지정 
		 * true이면 프로그램 시작시 수행되고  false이면 Context를 이용해 해당 component를 
		 * 수행시킨다.  */
		private String initstart;
		
		public String getInitstart() {
			return initstart;
		}
		public void setInitstart(String initstart) {
			this.initstart = initstart;
		}

		public Manifest() {
			super();
		}

		public Manifest(String componentKnd, String name) {
			super();
			this.componentKnd = componentKnd;
			this.name = name;
		}

		public String getComponentKnd() {
			return componentKnd;
		}
		public void setComponentKnd(String componentKnd) {
			this.componentKnd = componentKnd;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
		@Override
		public String toString() {
			return "Manifest [componentKnd=" + componentKnd + ", name=" + name
					+ ", initstart=" + initstart + "]";
		}
		
}
