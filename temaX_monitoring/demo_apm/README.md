# Preparar entorno

### Pre requisitos
* Tener instalado docker
* Tener instalado docker-compose


### docker-compose
Se requiere ejecutar el siguiente comando desde la raíz del proyecto para levantar los servicios necesarios:

```sh
docker-compose up
```

### Logger
Si es la primera vez que se levanta el proyecto en local, se requiere ejecutar los siguientes comandos:

1. Descargar filebit - El siguiente comando descargara y descomprimirá el paquete.

```sh
curl -L -O https://artifacts.elastic.co/downloads/beats/filebeat/filebeat-7.5.0-darwin-x86_64.tar.gz
tar xzvf filebeat-7.5.0-darwin-x86_64.tar.gz
cd filebeat-7.5.0-darwin-x86_64/
```

2. Modificar el archivo filebit.yml con información de conexión (predeterminados en el docker-compose):

```yml
output.elasticsearch:
  hosts: ["localhost:9200"]
setup.kibana:
  host: "localhost:5601"

filebeat.inputs:
  - type: log
    enabled: true
    paths:
      - /var/log/*.log
      ##- {ruta_absoluta_del_proyecto}/demo_apm/logs/*.log
```

3. Dentro de la carpeta de instalación de filebit ejecuta el siguiente comando:

```sh
./filebeat modules enable elasticsearch
```

4. Iniciar filebit - Si es la primera vez que se ejecuta filebit ejecuta el siguiente comando:
```sh
./filebeat setup
./filebeat -e
```
De lo contrario solo iniciar filebit con el siguiente comando:
```sh
./filebeat -e
```

### APM
Para visualizar los datos en el APM es necesario compilar generar el archivo jar del proyecto, posteriormente ejecutar el siguiente comando:

```sh
java -javaagent:./elastic-apm-agent.jar -jar ./target/demo-0.0.1-SNAPSHOT.jar
```

#### Nota
Tomese en cuenta que el nombre de servicio se obtiene del valor de la etiqueta **artifactId** del archivo **pom.xml**
