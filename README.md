#JavaSockets
Testing different methods of pinging a host from Android  
Testing different methods for Host Discovery.

##Host Discovery
Uses isReachable ping to scan the network. Uses SubnetUtils to get the range of IP addresses to scan.  
Insane mode - pings using all the available methods - multi-port TCP Socket, UDP Channel and ICMP ping, isReachable.
Gets the subnet mask and the ip address from WifiInfo.

##Wifi Info
Shows various Wifi Connection parameters.  

##ARP
After doing a normal Host Discovery, which usually returns ~80% accurate results, if ARP method is used, it returns full accurate Host Discovery result based on the /proc/net/arp cache.  

#TODO
1) Improve scanning for larger number of hosts  
2) Add option for scanning a specific range of hosts
3) Add raw packet ping (Native code)