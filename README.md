# slink

A Clojure library designed to Shorten Links -> slinks and redirect.

## Usage
Run in the interactive REPL

Compile all modules and start the server

You can use you http-client to make the calls

POST http://localhost:3000/links/create
request: 
{
 "url": "https://www.ahnegao.com.br",
 "user_id": "12"
}

response: {
 "url": "https://www.ahnegao.com.br",
 "surl": "iXg"
}

GET http://localhost:3000/iXg
response: {"redirects-to-link": "https://www.ahnegao.com.br"}



## License

Copyright © 2022 FIXME

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
