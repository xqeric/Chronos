1. create ssh keys
command: ssh-keygen -t rsa
2. copy ssh keys to remote servers (local otherwise the local worker can not be started)
command - ssh-copy-id -i ~/.ssh/id_rsa.pub remote-host
3. add slave node IP in slaves configuration file in master node, the slaves file in the spark/conf folder

4. add export SPARK_LOCAL_IP="local ip" in the spark-env.sh file which can be found under /spark/conf in slave node

5. run submit-chronos.sh
6. see it. good luck!

