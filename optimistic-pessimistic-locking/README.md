### apache benchmark concurrent request:

```shell
ab -n 25 -c 25 -p postdata.txt -T "application/x-www-form-urlencoded" http://localhost:8080/product/1/1
```

```shell
ab -n 25 -c 25 -p postdata.txt -T "application/x-www-form-urlencoded" http://localhost:8080/product/plock/1/1
```