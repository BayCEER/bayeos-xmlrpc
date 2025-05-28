package de.unibayreuth.bayceer.bayeos.client.object;



@ArraySerializable
public class MessungOrdner extends Objekt {
	
		@ArrayElement(20)
	    protected String bezeichnung;
		@ArrayElement(21)
		protected String beschreibung;
		@ArrayElement(22)
		protected Integer aufloesung;
		@ArrayElement(23)
		protected Integer intervalTypId;
		@ArrayElement(24)
		protected Integer timeZoneId;
		@ArrayElement(25)
		protected Double lat;
		@ArrayElement(26)
		protected Double lon;
		@ArrayElement(27)
		protected Double alt;
		
				
	    public MessungOrdner() {
			super();
		}
			 

		public String getBezeichnung() {
			return bezeichnung;
		}

		public void setBezeichnung(String bezeichnung) {
			this.bezeichnung = bezeichnung;
		}

		public String getBeschreibung() {
			return beschreibung;
		}

		public void setBeschreibung(String beschreibung) {
			this.beschreibung = beschreibung;
		}

		public Integer getAufloesung() {
			return aufloesung;
		}

		public void setAufloesung(Integer aufloesung) {
			this.aufloesung = aufloesung;
		}

		public Integer getIntervalTypId() {
			return intervalTypId;
		}

		public void setIntervalTypId(Integer intervalTypId) {
			this.intervalTypId = intervalTypId;
		}

		public Integer getTimeZoneId() {
			return timeZoneId;
		}

		public void setTimeZoneId(Integer timeZoneId) {
			this.timeZoneId = timeZoneId;
		}

		public Double getLat() {
			return lat;
		}

		public void setLat(Double lat) {
			this.lat = lat;
		}

		public Double getLon() {
			return lon;
		}

		public void setLon(Double lon) {
			this.lon = lon;
		}

		public Double getAlt() {
			return alt;
		}

		public void setAlt(Double alt) {
			this.alt = alt;
		}
		
	    

	

}
