### LRUCache for Graffiti requests - exercise

### What does it do ?
This is just a simple LRUCache for requests using AWS API Gateway lambda proxy integration. It aims to show a way to develop and test lambda functions locally and also use them in local environment.

The way this works is as follows:
 - we build our own lambda function locally and test it with unit tests
 - we use `sam-cli` tool to invoke and simulate its behaviour locally; what this tool does is launch a new docker container with the
 extracted jar file and invoke that using aws lambda environment declared inside `template.yml` file
 - once we are happy with the result, we use `sam local start-api` feature which reads `template.yml` and exposes all declared lambda functions
 on the hose machine under port 3000 where they can be proxied with nginx like we do with docker containers like solr, creative etc.

#### Installation
 - install docker
 - install homebrew: `/usr/bin/ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"
`
 - install sam-cli: `brew tap aws/tap && brew install aws-sam-cli`
 - pull repo && mvn package
 - start local api gateway with `sam local start-api` and use `http://127.0.0.1:3000/*` to access any dev graffiti urls (e.g. `http://127.0.0.1:3000/public/v1/sites?view=complete`)
 