java -Djava.rmi.server.codebase=file:`pwd`/ -Djava.security.policy=java.policy rainbow.RainbowServer ServerRainbow



java -Djava.security.policy=java.policy rainbow.RainbowClient localhost  ServerRainbow  4 22


