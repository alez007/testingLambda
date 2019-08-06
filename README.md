### LRUCache for Graffiti requests - exercise

#### Installation
 - install docker
 - install homebrew: `/usr/bin/ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"
`
 - install sam-cli: `brew tap aws/tap && brew install aws-sam-cli`
 - pull repo && mvn package
 - start local api gateway with `sam local start-api` and use `http://127.0.0.1:3000/*` to access any dev graffiti urls (e.g. `http://127.0.0.1:3000/public/v1/sites?view=complete`)
 