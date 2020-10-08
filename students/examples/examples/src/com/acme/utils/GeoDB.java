/*
 * Copyright (C) 2014 - 2020 T.N.Silverman, All rights reserved.
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.acme.utils;

import static java.util.Map.entry;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import static com.acme.utils.GeoDB.KeyValue.keyValue;

/**
 * The class GeoDB is a utility class containing sample geographic info about
 * countries, states, cities in the United States, Canada and Israel
 */
public final class GeoDB {
	private GeoDB() {
		super();
	}

	public static final List<KeyValue> COUNTRIES = List.of(
		keyValue("US", "United States"),
		keyValue("CA", "Canada"),
		keyValue("DE", "Germany"),
		keyValue("IL", "\u05d9\u05e9\u05e8\u05d0\u05dc"));
	public static final Map<String, List<KeyValue>> STATES = Map.ofEntries(
		entry(
			"US",
			List.of(
				keyValue("AL", "Alabama"),
				keyValue("AK", "Alaska"),
				keyValue("AZ", "Arizona"),
				keyValue("AR", "Arkansas"),
				keyValue("CA", "California"),
				keyValue("CO", "Colorado"),
				keyValue("CT", "Connecticut"),
				keyValue("DE", "Delaware"),
				keyValue("FL", "Florida"),
				keyValue("GA", "Georgia"),
				keyValue("HI", "Hawaii"),
				keyValue("ID", "Idaho"),
				keyValue("IL", "Illinois"),
				keyValue("IN", "Indiana"),
				keyValue("IA", "Iowa"),
				keyValue("KS", "Kansas"),
				keyValue("KY", "Kentucky"),
				keyValue("LA", "Louisiana"),
				keyValue("ME", "Maine"),
				keyValue("MD", "Maryland"),
				keyValue("MA", "Massachusetts"),
				keyValue("MI", "Michigan"),
				keyValue("MN", "Minnesota"),
				keyValue("MS", "Mississippi"),
				keyValue("MO", "Missouri"),
				keyValue("MT", "Montana"),
				keyValue("NE", "Nebraska"),
				keyValue("NV", "Nevada"),
				keyValue("NH", "New Hampshire"),
				keyValue("NJ", "New Jersey"),
				keyValue("NM", "New Mexico"),
				keyValue("NY", "New York"),
				keyValue("NC", "North Carolina"),
				keyValue("ND", "North Dakota"),
				keyValue("OH", "Ohio"),
				keyValue("OK", "Oklahoma"),
				keyValue("OR", "Oregon"),
				keyValue("PA", "Pennsylvania"),
				keyValue("RI", "Rhode Island"),
				keyValue("SC", "South Carolina"),
				keyValue("SD", "South Dakota"),
				keyValue("TN", "Tennessee"),
				keyValue("TX", "Texas"),
				keyValue("UT", "Utah"),
				keyValue("VT", "Vermont"),
				keyValue("VA", "Virginia"),
				keyValue("WA", "Washington"),
				keyValue("WV", "West Virginia"),
				keyValue("WI", "Wisconsin"),
				keyValue("WY", "Wyoming"),
				keyValue("DC", "District of Columbia"),
				keyValue("MH", "Marshall Islands"),
				keyValue("AE", "Armed Forces"),
				keyValue("AA", "Armed Forces Americas"),
				keyValue("AP", "Armed Forces Pacific"))),
		entry(
			"CA",
			List.of(
				keyValue("AB", "Alberta"),
				keyValue("BC", "British Columbia"),
				keyValue("MB", "Manitoba"),
				keyValue("NB", "New Brunswick"),
				keyValue("NL", "Newfoundland and Labrador"),
				keyValue("NT", "Northwest Territories"),
				keyValue("NS", "Nova Scotia"),
				keyValue("NU", "Nunavut"),
				keyValue("ON", "Ontario"),
				keyValue("PE", "Prince Edward Island"),
				keyValue("QC", "Quebec"),
				keyValue("SK", "Saskatchewan"),
				keyValue("YT", "Yukon"))),
		entry(
			"DE",
			List.of(
				keyValue("BW", "Baden-Württemberg"),
				keyValue("BY", "Bavaria"),
				keyValue("BE", "Berlin"),
				keyValue("BB", "Brandenburg"),
				keyValue("HB", "Bremen"),
				keyValue("HH", "Hamburg"),
				keyValue("HE", "Hesse"),
				keyValue("NI", "Lower Saxony"),
				keyValue("MV", "Mecklenburg-Vorpommern"),
				keyValue("NW", "North Rhine-Westphalia"),
				keyValue("RP", "Rhineland-Palatinate"),
				keyValue("SL", "Saarland"),
				keyValue("SN", "Saxony"),
				keyValue("ST", "Saxony-Anhalt"),
				keyValue("SH", "Schleswig-Holstein"),
				keyValue("TH", "Thuringia"))),
		entry(
			"IL",
			List.of(
				keyValue("\u05de\u05e8\u05db\u05d6", "\u05de\u05e8\u05db\u05d6"),
				keyValue("\u05d3\u05e8\u05d5\u05dd", "\u05d3\u05e8\u05d5\u05dd"),
				keyValue("\u05e6\u05e4\u05d5\u05df", "\u05e6\u05e4\u05d5\u05df"))));
	public static final Map<KeyValue, List<String>> CITIES = Map.ofEntries(
		entry(
			keyValue("US", "AL"),
			List.of(
				"Birmingham",
				"Montgomery",
				"Mobile",
				"Huntsville",
				"Tuscaloosa",
				"Hoover",
				"Dothan",
				"Auburn",
				"Decatur",
				"Madison",
				"Florence",
				"Phenix City")),
		entry(
			keyValue("US", "AK"),
			List.of(
				"Anchorage",
				"Juneau ",
				"Fairbanks",
				"Eagle River",
				"Badger",
				"Knik-Fairview",
				"College",
				"Wasilla",
				"Sitka",
				"Lakes",
				"Tanaina",
				"Lakes",
				"Ketchikan")),
		entry(
			keyValue("US", "AZ"),
			List.of(
				"Phoenix",
				"Juneau",
				"Fairbanks",
				"Eagle River",
				"Badger",
				"Knik-Fairview",
				"College",
				"Wasilla",
				"Sitka",
				"Lakes",
				"Tanaina",
				"Lakes",
				"Ketchikan")),
		entry(
			keyValue("US", "AR"),
			List.of(
				"Fort Smith",
				"Fayetteville",
				"Springdale",
				"Jonesboro",
				"North",
				"Conway",
				"Rogers",
				"Pine Bluff",
				"Bentonville",
				"Hot Springs",
				"Hot Springs National Park")),
		entry(
			keyValue("US", "CA"),
			List.of(
				"Los Angeles",
				"San Diego",
				"San Jose",
				"San Francisco",
				"Fresno",
				"Sacramento",
				"Long Beach",
				"Oakland",
				"Bakersfield",
				"Anaheim",
				"Santa Ana",
				"Riverside")),
		entry(
			keyValue("US", "CO"),
			List.of(
				"Denver",
				"Colorado Springs",
				"Aurora",
				"Fort Collins",
				"Lakewood",
				"Thornton",
				"Arvada",
				"Westminster",
				"Centennial",
				"Pueblo",
				"Boulder",
				"Greeley")),
		entry(
			keyValue("US", "CT"),
			List.of(
				"Bridgeport",
				"New Haven",
				"Stamford",
				"Hartford",
				"North Stamford",
				"Waterbury",
				"Norwalk",
				"Danbury",
				"East Norwalk",
				"New Britain",
				"West Hartford",
				"Bristol")),
		entry(
			keyValue("US", "DE"),
			List.of(
				"Wilmington",
				"Dover",
				"Newark",
				"Middletown",
				"Bear",
				"Brookside",
				"Glasgow",
				"Hockessin",
				"Smyrna",
				"PikeCreekValley",
				"Milford",
				"Claymont")),
		entry(
			keyValue("US", "FL"),
			List.of(
				"Jacksonville",
				"Miami",
				"Tampa",
				"Orlando",
				"St.Petersburg",
				"Hialeah",
				"Tallahassee",
				"FortLauderdale",
				"CapeCoral",
				"PembrokePines",
				"PortSaintLucie",
				"Hollywood")),
		entry(
			keyValue("US", "GA"),
			List.of(
				"Atlanta",
				"Columbus",
				"Savannah",
				"Athens",
				"Sandy Springs",
				"Roswell",
				"Macon",
				"Johns Creek",
				"Albany",
				"Warner Robins",
				"Alpharetta",
				"Marietta")),
		entry(
			keyValue("US", "ID"),
			List.of(
				"Boise",
				"Meridian",
				"Nampa",
				"Idaho Falls",
				"Pocatello",
				"Caldwell",
				"Coeur d'Alene",
				"Twin Falls",
				"Lewiston",
				"Lewiston Orchards",
				"Post Falls",
				"Rexburg")),
		entry(
			keyValue("US", "IL"),
			List.of(
				"Chicago",
				"Aurora",
				"Rockford",
				"Joliet",
				"Naperville",
				"Springfield",
				"Peoria",
				"North Peoria",
				"Elgin",
				"Waukegan",
				"Champaign",
				"Cicero")),
		entry(
			keyValue("US", "IN"),
			List.of(
				"Indianapolis",
				"Fort Wayne",
				"Evansville",
				"South Bend",
				"Carmel",
				"Bloomington",
				"Hammond",
				"Gary",
				"Fishers",
				"Lafayette",
				"Muncie",
				"Terre Haute")),
		entry(
			keyValue("US", "IA"),
			List.of(
				"Des Moines",
				"Cedar Rapids",
				"Davenport",
				"Sioux City",
				"Iowa City",
				"Waterloo",
				"Ames",
				"West Des Moines",
				"Council Bluffs",
				"Dubuque",
				"Ankeny",
				"Urbandale")),
		entry(
			keyValue("US", "KS"),
			List.of(
				"Wichita",
				"Overland Park",
				"Kansas City",
				"Olathe",
				"Topeka",
				"Lawrence",
				"Shawnee",
				"Manhattan",
				"Lenexa",
				"Salina",
				"Hutchinson",
				"Leavenworth")),
		entry(
			keyValue("US", "KY"),
			List.of(
				"Lexington-Fayette",
				"Meads",
				"Ironville",
				"Louisville",
				"Lexington",
				"Bowling Green",
				"Owensboro",
				"Covington",
				"Richmond",
				"Georgetown",
				"Florence",
				"Hopkinsville")),
		entry(
			keyValue("US", "LA"),
			List.of(
				"New Orleans",
				"Baton Rouge",
				"Shreveport",
				"Metairie Terrace",
				"Metairie",
				"Lafayette",
				"Lake Charles",
				"Bossier City",
				"Kenner",
				"Monroe",
				"Alexandria",
				"Houma")),
		entry(
			keyValue("US", "ME"),
			List.of(
				"Portland",
				"Lewiston",
				"Bangor",
				"West Scarborough",
				"South Portland",
				"South Portland Gardens",
				"Auburn",
				"Biddeford",
				"Sanford",
				"Saco",
				"Augusta",
				"Westbrook")),
		entry(
			keyValue("US", "MD"),
			List.of(
				"Baltimore",
				"Columbia",
				"Germantown",
				"Silver Spring",
				"Frederick",
				"Waldorf",
				"Glen Burnie",
				"Gaithersburg",
				"Rockville",
				"Ellicott City",
				"Dundalk",
				"Bethesda")),
		entry(
			keyValue("US", "MA"),
			List.of(
				"Boston",
				"Worcester",
				"Springfield",
				"Lowell",
				"Cambridge",
				"Brockton",
				"New Bedford",
				"Quincy",
				"Lynn",
				"Newton",
				"Fall River",
				"Somerville")),
		entry(
			keyValue("US", "MI"),
			List.of(
				"Detroit",
				"Grand Rapids",
				"Warren",
				"Sterling Heights",
				"Ann Arbor",
				"Lansing",
				"Charter Township of Clinton",
				"Flint",
				"Dearborn",
				"Livonia",
				"Canton",
				"Troy")),
		entry(
			keyValue("US", "MN"),
			List.of(
				"Minneapolis",
				"Saint Paul",
				"Rochester",
				"Bloomington",
				"Duluth",
				"Brooklyn Park",
				"Plymouth",
				"Maple Grove",
				"Woodbury",
				"Eagan",
				"Saint Cloud",
				"Eden Prairie")),
		entry(
			keyValue("US", "MS"),
			List.of(
				"Jackson",
				"Gulfport",
				"West Gulfport",
				"Southaven",
				"Hattiesburg",
				"Biloxi",
				"Meridian",
				"Olive Branch",
				"Tupelo",
				"Greenville",
				"Horn Lake",
				"Pearl")),
		entry(
			keyValue("US", "MO"),
			List.of(
				"Kansas City",
				"St Louis",
				"Springfield",
				"Columbia",
				"Independence",
				"Independence, Mo",
				"Lee's Summit",
				"O'Fallon",
				"Saint Joseph",
				"Saint Charles",
				"Blue Springs",
				"City of Saint Peters")),
		entry(
			keyValue("US", "MT"),
			List.of(
				"Billings",
				"Missoula",
				"Great Falls",
				"Bozeman",
				"Butte",
				"Helena",
				"Kalispell",
				"Havre",
				"Anaconda",
				"Miles City",
				"Helena Valley Southeast",
				"Belgrade")),
		entry(
			keyValue("US", "NE"),
			List.of(
				"Omaha",
				"Lincoln",
				"Bellevue",
				"Grand Island",
				"Kearney",
				"Fremont",
				"Hastings",
				"Norfolk",
				"North Platte",
				"Columbus",
				"Papillion",
				"La Vista")),
		entry(
			keyValue("US", "NH"),
			List.of(
				"Manchester",
				"Nashua",
				"Concord",
				"East Concord",
				"Derry Village",
				"Dover",
				"Rochester",
				"Salem",
				"Merrimack",
				"Keene",
				"Derry",
				"Portsmouth")),
		entry(
			keyValue("US", "NJ"),
			List.of(
				"Newark",
				"Jersey City",
				"Paterson",
				"Elizabeth",
				"Edison",
				"Toms River",
				"Clifton",
				"Trenton",
				"Camden",
				"Passaic",
				"Cherry Hill",
				"Union City")),
		entry(
			keyValue("US", "NM"),
			List.of(
				"Albuquerque",
				"Las Cruces",
				"Rio Rancho",
				"Enchanted Hills",
				"Santa Fe",
				"Roswell",
				"Farmington",
				"South Valley",
				"Clovis",
				"Hobbs",
				"Alamogordo",
				"Carlsbad")),
		entry(
			keyValue("US", "NY"),
			List.of(
				"New York",
				"Brooklyn",
				"Borough of Queens",
				"Manhattan",
				"Bronx",
				"Staten Island",
				"Buffalo",
				"Jamaica",
				"Rochester",
				"Yonkers",
				"East Flatbush",
				"Washington Heights")),
		entry(
			keyValue("US", "NC"),
			List.of(
				"Charlotte",
				"Raleigh",
				"Greensboro",
				"Durham",
				"Winston-Salem",
				"Fayetteville",
				"Cary",
				"Wilmington",
				"High Point",
				"Greenville",
				"Asheville",
				"Concord")),
		entry(
			keyValue("US", "ND"),
			List.of(
				"Fargo",
				"Bismarck",
				"Grand Forks",
				"Minot",
				"West Fargo",
				"Williston",
				"Dickinson",
				"Mandan",
				"Jamestown",
				"Wahpeton",
				"Devils Lake",
				"Watford City")),
		entry(
			keyValue("US", "OH"),
			List.of(
				"Columbus",
				"Cleveland",
				"Cincinnati",
				"Toledo",
				"Akron",
				"Dayton",
				"Parma",
				"Canton",
				"Youngstown",
				"Lorain",
				"Hamilton",
				"Springfield")),
		entry(
			keyValue("US", "OK"),
			List.of(
				"Oklahoma City",
				"Tulsa",
				"Norman",
				"Broken Arrow",
				"Lawton",
				"Edmond",
				"Moore",
				"Midwest City",
				"Enid",
				"Stillwater",
				"Muskogee",
				"Bartlesville")),
		entry(
			keyValue("US", "OR"),
			List.of(
				"Portland",
				"Salem",
				"Eugene",
				"Gresham",
				"Hillsboro",
				"Beaverton",
				"Bend",
				"Medford",
				"Springfield",
				"Corvallis",
				"Albany",
				"Tigard")),
		entry(
			keyValue("US", "PA"),
			List.of(
				"Phila",
				"Pittsburgh",
				"Allentown",
				"Erie",
				"Reading",
				"Scranton",
				"Bethlehem",
				"Bensalem",
				"Lancaster",
				"Abington",
				"Levittown",
				"Harrisburg")),
		entry(
			keyValue("US", "RI"),
			List.of(
				"Providence",
				"Warwick",
				"Cranston",
				"Pawtucket",
				"East Providence",
				"Woonsocket",
				"Coventry",
				"Cumberland",
				"North Providence",
				"South Kingstown",
				"West Warwick",
				"Johnston")),
		entry(
			keyValue("US", "SC"),
			List.of(
				"Columbia",
				"Charleston",
				"North Charleston",
				"Mt. Pleasant",
				"Rock Hill",
				"Greenville",
				"Summerville",
				"Sumter",
				"Goose Creek",
				"Hilton Head Island",
				"Florence",
				"Spartanburg")),
		entry(
			keyValue("US", "SD"),
			List.of(
				"Sioux Falls",
				"Rapid City",
				"Aberdeen",
				"Brookings",
				"Watertown",
				"Mitchell",
				"Yankton",
				"Pierre",
				"Huron",
				"Spearfish",
				"Vermillion",
				"Brandon")),
		entry(
			keyValue("US", "TN"),
			List.of(
				"Memphis",
				"Nashville",
				"Knoxville",
				"Chattanooga",
				"East Chattanooga",
				"Clarksville",
				"Murfreesboro",
				"Franklin",
				"Jackson",
				"Johnson City",
				"Bartlett",
				"Hendersonville")),
		entry(
			keyValue("US", "TX"),
			List.of(
				"Houston",
				"San Antonio",
				"Dallas",
				"Austin",
				"Fort Worth",
				"El Paso",
				"Arlington",
				"Corpus Christi",
				"Plano",
				"Laredo",
				"Lubbock",
				"Garland")),
		entry(
			keyValue("US", "UT"),
			List.of(
				"Salt Lake City",
				"West Valley City",
				"Provo",
				"West Jordan",
				"Orem",
				"Sandy",
				"Ogden",
				"Layton",
				"Saint George",
				"South Jordan",
				"Millcreek",
				"Taylorsville")),
		entry(
			keyValue("US", "VT"),
			List.of(
				"Burlington",
				"South Burlington",
				"Colchester",
				"Rutland",
				"Essex Junction",
				"Hartford",
				"Bennington",
				"Barre",
				"Williston",
				"Montpelier",
				"St Johnsbury",
				"Brattleboro")),
		entry(
			keyValue("US", "VA"),
			List.of(
				"Virginia Beach",
				"Norfolk",
				"Chesapeake",
				"Richmond",
				"Arlington",
				"Newport News",
				"Alexandria",
				"East Hampton",
				"Hampton",
				"Roanoke",
				"Portsmouth Heights",
				"Portsmouth")),
		entry(
			keyValue("US", "WA"),
			List.of(
				"Seattle",
				"Spokane",
				"Tacoma",
				"Vancouver",
				"Bellevue",
				"Kent",
				"Everett",
				"Renton",
				"Federal Way",
				"Spokane Valley",
				"Yakima",
				"Kirkland")),
		entry(
			keyValue("US", "WV"),
			List.of(
				"Charleston",
				"Huntington",
				"Parkersburg",
				"Morgantown",
				"Wheeling",
				"Weirton Heights",
				"Weirton",
				"Fairmont",
				"Martinsburg",
				"Beckley",
				"Clarksburg",
				"Teays Valley")),
		entry(
			keyValue("US", "WI"),
			List.of(
				"Milwaukee",
				"Madison",
				"Green Bay",
				"Kenosha",
				"Racine",
				"Appleton",
				"Waukesha",
				"Eau Claire",
				"Oshkosh",
				"Janesville",
				"West Allis",
				"La Crosse")),
		entry(
			keyValue("US", "WY"),
			List.of(
				"Cheyenne",
				"Casper",
				"Gillette",
				"Laramie",
				"Rock Springs",
				"Sheridan",
				"Green River",
				"Evanston",
				"Riverton",
				"Jackson",
				"Cody",
				"Rawlins")),
		entry(keyValue("US", "DC"), List.of("Washington", "Shaw", "Adams Morgan", "Chevy Chase", "Bloomingdale")),
		entry(keyValue("US", "MH"), List.of("Majuro", "Ebeye")),
		entry(keyValue("US", "AE"), List.of("Armed Forces Africa", "Armed Forces Canada", "Armed Forces Europe", "Armed Forces Middle East")),
		entry(keyValue("US", "AA"), List.of("Armed Forces Americas")),
		entry(keyValue("US", "AP"), List.of("Armed Forces Pacific")),
		entry(keyValue("CA", "AB"), List.of("Edmonton")),
		entry(keyValue("CA", "BC"), List.of("Victoria")),
		entry(keyValue("CA", "MB"), List.of("Winnipeg")),
		entry(keyValue("CA", "NB"), List.of("Fredericton")),
		entry(keyValue("CA", "NL"), List.of("St. John's")),
		entry(keyValue("CA", "NT"), List.of("Yellowknife")),
		entry(keyValue("CA", "NS"), List.of("Halifax")),
		entry(keyValue("CA", "NU"), List.of("Iqaluit")),
		entry(keyValue("CA", "ON"), List.of("Toronto")),
		entry(keyValue("CA", "PE"), List.of("Charlottetown")),
		entry(keyValue("CA", "QC"), List.of("Quebec City")),
		entry(keyValue("CA", "SK"), List.of("Regina")),
		entry(keyValue("CA", "YT"), List.of("Whitehorse")),
		entry(
			keyValue("DE", "BW"),
			List.of(
				"Aalen",
				"Baden-Baden",
				"Böblingen",
				"Esslingen am Neckar",
				"Freiburg im Breisgau",
				"Friedrichshafen",
				"Göppingen",
				"Heidelberg",
				"Heilbronn",
				"Karlsruhe",
				"Konstanz",
				"Ludwigsburg",
				"Mannheim",
				"Offenburg",
				"Pforzheim",
				"Ravensburg",
				"Reutlingen",
				"Schwäbisch Gmünd",
				"Sindelfingen",
				"Stuttgart",
				"Tübingen",
				"Ulm",
				"Villingen-Schwenningen",
				"Waiblingen")),
		entry(
			keyValue("DE", "BY"),
			List.of(
				"Aschaffenburg",
				"Augsburg",
				"Bamberg",
				"Bayreuth",
				"Erlangen",
				"Fürth",
				"Ingolstadt",
				"Kempten (Allgäu)",
				"Landshut",
				"München [Munich]",
				"Neu-Ulm",
				"Nürnberg [Nuremberg]",
				"Passau",
				"Regensburg",
				"Rosenheim",
				"Schweinfurt",
				"Würzburg")),
		entry(keyValue("DE", "BE"), List.of("Berlin")),
		entry(keyValue("DE", "BB"), List.of("Brandenburg an der Havel", "Cottbus (Chóśebuz)", "Frankfurt (Oder)", "Potsdam")),
		entry(keyValue("DE", "HB"), List.of("Bremen", "Bremerhaven")),
		entry(keyValue("DE", "HH"), List.of("Hamburg")),
		entry(
			keyValue("DE", "HE"),
			List.of(
				"Bad Homburg vor der Höhe",
				"Darmstadt",
				"Frankfurt am Main",
				"Fulda",
				"Gießen",
				"Hanau",
				"Kassel",
				"Marburg",
				"Offenbach am Main",
				"Rüsselsheim am Main",
				"Wetzlar",
				"Wiesbaden")),
		entry(
			keyValue("DE", "NI"),
			List.of(
				"Braunschweig [Brunswick]",
				"Celle",
				"Delmenhorst",
				"Garbsen",
				"Goslar",
				"Göttingen",
				"Hameln",
				"Hannover [Hanover]",
				"Hildesheim",
				"Langenhagen",
				"Lingen (Ems)",
				"Lüneburg",
				"Nordhorn",
				"Oldenburg (Oldenburg)",
				"Osnabrück",
				"Salzgitter",
				"Wilhelmshaven",
				"Wolfenbüttel",
				"Wolfsburg")),
		entry(keyValue("DE", "MV"), List.of("Greifswald", "Neubrandenburg", "Rostock", "Schwerin", "Stralsund")),
		entry(
			keyValue("DE", "NW"),
			List.of(
				"Aachen [Aix-la-Chapelle]",
				"Ahlen",
				"Arnsberg",
				"Bad Salzuflen",
				"Bergheim",
				"Bergisch Gladbach",
				"Bielefeld",
				"Bocholt",
				"Bochum",
				"Bonn",
				"Bottrop",
				"Castrop-Rauxel",
				"Detmold",
				"Dinslaken",
				"Dormagen",
				"Dorsten",
				"Dortmund",
				"Duisburg",
				"Düren",
				"Düsseldorf [Dusseldorf]",
				"Erftstadt",
				"Eschweiler",
				"Essen",
				"Euskirchen",
				"Frechen",
				"Gelsenkirchen",
				"Gladbeck",
				"Grevenbroich",
				"Gummersbach",
				"Gütersloh",
				"Hagen",
				"Hamm",
				"Hattingen",
				"Herford",
				"Herne",
				"Herten",
				"Hilden",
				"Hürth",
				"Ibbenbüren",
				"Iserlohn",
				"Kerpen",
				"Kleve",
				"Köln [Cologne]",
				"Krefeld",
				"Langenfeld (Rheinland)",
				"Leverkusen",
				"Lippstadt",
				"Lüdenscheid",
				"Lünen",
				"Marl",
				"Meerbusch",
				"Menden (Sauerland)",
				"Minden",
				"Moers",
				"Mönchengladbach",
				"Mülheim an der Ruhr",
				"Münster",
				"Neuss",
				"Oberhausen",
				"Paderborn",
				"Pulheim",
				"Ratingen",
				"Recklinghausen",
				"Remscheid",
				"Rheine",
				"Sankt Augustin",
				"Siegen",
				"Solingen",
				"Stolberg (Rhld.)",
				"Troisdorf",
				"Unna",
				"Velbert",
				"Viersen",
				"Wesel",
				"Willich",
				"Witten",
				"Wuppertal")),
		entry(
			keyValue("DE", "RP"),
			List.of(
				"Bad Kreuznach",
				"Kaiserslautern",
				"Koblenz",
				"Ludwigshafen am Rhein",
				"Mainz",
				"Neustadt an der Weinstraße",
				"Neuwied",
				"Speyer",
				"Trier",
				"Worms")),
		entry(keyValue("DE", "SL"), List.of("Saarbrücken")),
		entry(keyValue("DE", "SN"), List.of("Chemnitz", "Dresden", "Görlitz", "Leipzig", "Plauen", "Zwickau")),
		entry(keyValue("DE", "ST"), List.of("Dessau-Roßlau", "Halle (Saale)", "Magdeburg")),
		entry(keyValue("DE", "SH"), List.of("Flensburg", "Kiel", "Lübeck", "Neumünster", "Norderstedt")),
		entry(keyValue("DE", "TH"), List.of("Erfurt", "Gera", "Jena", "Weimar")),
		entry(
			keyValue("IL", "\u05de\u05e8\u05db\u05d6"),
			List.of(
				"\u05ea\u05dc \u05d0\u05d1\u05d9\u05d1",
				"\u05d9\u05e8\u05d5\u05e9\u05dc\u05d9\u05dd",
				"\u05e0\u05ea\u05e0\u05d9\u05d4",
				"\u05d4\u05e8\u05e6\u05dc\u05d9\u05d4",
				"\u05d1\u05ea-\u05d9\u05dd",
				"\u05d7\u05d5\u05dc\u05d5\u05df",
				"\u05e8\u05de\u05ea-\u05d2\u05df",
				"\u05e8\u05d0\u05e9\u05d5\u05df-\u05dc\u05e6\u05d9\u05d5\u05df",
				"\u05d0\u05e8\u05d9\u05d0\u05dc",
				"\u05d9\u05d1\u05e0\u05d4",
				"\u05dc\u05d5\u05d3",
				"\u05e8\u05de\u05dc\u05d4",
				"\u05de\u05d5\u05d3\u05d9\u05e2\u05d9\u05df",
				"\u05e8\u05e2\u05d5\u05ea-\u05de\u05db\u05d1\u05d9\u05dd",
				"\u05e9\u05d5\u05d4\u05dd",
				"\u05d9\u05d4\u05d5\u05d3",
				"\u05d0\u05d5\u05e8-\u05d9\u05d4\u05d5\u05d3\u05d4")),
		entry(
			keyValue("IL", "\u05d3\u05e8\u05d5\u05dd"),
			List.of(
				"\u05d1\u05d0\u05e8 \u05e9\u05d1\u05e2",
				"\u05d0\u05e9\u05d3\u05d5\u05d3",
				"\u05d0\u05e9\u05e7\u05dc\u05d5\u05df",
				"\u05d3\u05d9\u05de\u05d5\u05e0\u05d4",
				"\u05d0\u05d9\u05dc\u05ea",
				"\u05d0\u05d5\u05e4\u05e7\u05d9\u05dd",
				"\u05e0\u05ea\u05d9\u05d1\u05d5\u05ea",
				"\u05e9\u05d3\u05e8\u05d5\u05ea",
				"\u05d0\u05d5\u05e8-\u05e2\u05e7\u05d9\u05d1\u05d4")),
		entry(
			keyValue("IL", "\u05e6\u05e4\u05d5\u05df"),
			List.of(
				"\u05d7\u05d9\u05e4\u05d4",
				"\u05d7\u05d3\u05e8\u05d4",
				"\u05e0\u05d4\u05e8\u05d9\u05d4",
				"\u05e0\u05e6\u05e8\u05ea",
				"\u05e2\u05db\u05d5",
				"\u05d0\u05d5\u05e8-\u05e2\u05e7\u05d9\u05d1\u05d4")));

	public static KeyValue countryByCountryCode(String code) {
		return COUNTRIES.stream().filter(kv -> kv.getKey().equals(code)).findAny().orElse(null);
	}

	public static List<KeyValue> statesByCountryCode(String code) {
		return STATES.get(code);
	}

	public static List<String> citiesByCodes(String countryCode, String stateCode) {
		return CITIES.get(keyValue(countryCode, stateCode));
	}

	public final static class KeyValue extends AbstractMap.SimpleEntry<String, String> {
		public KeyValue(String key, String value) {
			super(key, value);
		}

		public final static KeyValue keyValue(String key, String value) {
			return new KeyValue(key, value);
		}

		@Override
		public String toString() {
			return getValue();
		}
	}
}
