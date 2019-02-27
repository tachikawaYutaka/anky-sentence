# anky-sentence

## flyway

```
mvn flyway:clean -Dflyway.user=test -Dflyway.password=hoge00 -Dflyway.url=jdbc:mysql://localhost:3306/anky_sentence?serverTimezone=JST -Dflyway.driver=com.mysql.cj.jdbc.Driver -Dflyway.placeholderReplacement=false
```

```
mvn flyway:migrate -Dflyway.user=test -Dflyway.password=hoge00 -Dflyway.url=jdbc:mysql://localhost:3306/anky_sentence?serverTimezone=JST -Dflyway.driver=com.mysql.cj.jdbc.Driver -Dflyway.placeholderReplacement=false
```
