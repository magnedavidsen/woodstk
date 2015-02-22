Using docker to run Eventstore:

1. docker pull adbrowne/eventstore
2. docker run -d -p 2113:2113 -p 1113:1113 adbrowne/eventstore
3. Then to use the Web UI: http://192.168.59.103:2113/web/index.html (192.168.59.103 might differ, run 'boot2docker ip')