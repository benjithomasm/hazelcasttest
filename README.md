# hazelcasttestV2

ServerController - Core Controller and entry point of the program. It has logic to co ordinate start a server as well as check if peers are running.
                   
TcpServer - Runs a server on a specific port (from configuration) - In V2, only start IP is mentioned in conf, the TcpServer will pick the next one if not available

TcpClient - It has a heartbeat method to check if a server is running on a specific port - it also scans the pre defined IP addresses to see if there are distributed processes

HTConstants - contains the configurations

# hazelcasttest
ServerController - Core Controller and entry point of the program. 
                   It has logic to co ordinate start a server as well as check if peers are running

TcpServer - Runs a server on a specific port (from configuration) - If a port is occupied, it goes to the next node

TcpClient - It has a heartbeat method to check if a server is running on a specific port and tests if its no other processes by issuing a command

ServerList - It is a bean which reads configuration from spring configuration on what ports the server should run. 
             For a single machine, I could potentially start on a port and keep incrementing to 10 nodes. 
             But I picked a configurable list so that way it can be on different machines as well. 
             One logical group in multiple machine. In the current configuration, I have configured only 3 but can be any number.
             
	
	
	
	Add nodes by updating context.xml 
	
	<bean id="iplist" class="com.benjamin.hazelcasttest.ServerList">
		<constructor-arg>
			<list>
				<value>127.0.0.1:9066</value>
				<value>127.0.0.1:9067</value>
				<value>127.0.0.1:9068</value>
				<value>127.0.0.1:9069</value>
			</list>
		</constructor-arg>
	</bean>	
