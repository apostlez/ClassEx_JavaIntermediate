package edu.jaen.java.framework.util.parser;

/**Manifext.xml  ������ �о Component �ϳ��� �����ϱ� ���� VO */
public class Manifest {
		/**component ����  activity :1,  service  :2 */
		private String componentKnd;   		
		/**component  �̸�
		 * component �̸����� �ش� component�� �����Ų��.*/
		private String name;		
		/**���α׷� ���۽� �ش� component�� �����ų�� ���θ� ���� 
		 * true�̸� ���α׷� ���۽� ����ǰ�  false�̸� Context�� �̿��� �ش� component�� 
		 * �����Ų��.  */
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
