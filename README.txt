API_URL: http://localhost:8080/swagger-ui.html#/

USED_IDE: Spring Tools Suite 4

EXTERNAL_LIBRARIES:
  springfox-swagger2
  springfox-swagger-ui
  json
  jackson-core
  mapstruct
  mapstruct-processor
  powermock-module-junit4
  rest-assured

PROBLEMS_IN:
  Average call duration grouped by country code (https://en.wikipedia.org/wiki/MSISDN) 
	'I grouped just by Origin calls. I don't know if was by both Origin / Destination'

  Relationship between OK/KO calls
	'I don't know exactly what this means. I just did a count of KO / OK calls

  Word occurrence ranking for the given words in message_content field
	'I did a message_content ranking. It seems that by separate words was strange, because there are words like '1.1'

  CountryCodes related
	'I tried to extract the CountryCode from the MSISDN, but i'm not sure if my method it's ok at all

POST_DATA:
  I wanted to use Lombok library, but, besides of the POM dependency, it requires a plugin installation on the IDE,
  so i had to use standars getters and setters
  