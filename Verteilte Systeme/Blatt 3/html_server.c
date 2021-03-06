/* 
 Beispiel TCP Echo-Server fuer mehrere Clients 
 Aus Stevens: Unix Network Programming 
 CWe 10/2016: adaptiert fuer Ubuntu 16.04 64Bit
*/

#include <unistd.h>    // fuer read, write etc.
#include <stdlib.h>     // fuer exit
#include <stdio.h>
#include <string.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>

#define SRV_PORT 8008
#define MAX_SOCK 10
#define MAXLINE 512

// Vorwaertsdeklarationen intern
void str_echo(int); 
void err_abort(char *str);

int main(int argc, char *argv[]) {

	// Deskriptoren, Adresslaenge, Prozess-ID 
	int sockfd, newsockfd, alen, pid;
	int reuse = 1;

	// Socket Adressen
	struct sockaddr_in cli_addr, srv_addr;

	// TCP-Socket erzeugen
	if((sockfd=socket(AF_INET, SOCK_STREAM, 0)) < 0) {
		err_abort("Kann Stream-Socket nicht oeffnen!");
	}

	if(setsockopt(sockfd,SOL_SOCKET,SO_REUSEADDR,&reuse,sizeof(reuse))<0){
		err_abort("Kann Socketoption nicht setzen!");
	}

	// Binden der lokalen Adresse damit Clients uns erreichen
	memset((void *)&srv_addr, '\0', sizeof(srv_addr));
	srv_addr.sin_family = AF_INET;
	srv_addr.sin_addr.s_addr = htonl(INADDR_ANY);
	srv_addr.sin_port = htons(SRV_PORT);
	if( bind(sockfd, (struct sockaddr *)&srv_addr,
		sizeof(srv_addr)) < 0 ) {
		err_abort("Kann lokale Adresse nicht binden, laeuft fremder Server?");
	}

	// Warteschlange fuer TCP-Socket einrichten
	listen(sockfd,5);
	printf("HTML Echo-Server: bereit ...\n");

	for(;;){
		alen = sizeof(cli_addr);

		// Verbindung aufbauen
		newsockfd = accept(sockfd,(struct sockaddr *)&cli_addr,&alen);
		if(newsockfd < 0){
			err_abort("Fehler beim Verbindungsaufbau!");
		}

		// fuer jede Verbindung einen Kindprozess erzeugen
		if((pid = fork()) < 0){
			err_abort("Fehler beim Erzeugen eines Kindprozesses!");
		}else if(pid == 0){
			close(sockfd);
			str_echo(newsockfd);
			exit(0);
		}
		close(newsockfd);
	}
} 

/* str_echo: Lesen von Daten vom Socket und an den Client zuruecksenden 
*/
void str_echo(int sockfd) {
	int n;
	char in[MAXLINE], out[MAXLINE+6];

	memset((void *)in,'\0',MAXLINE);

	for(;;){
		// Daten vom Socket lesen
		n = read(sockfd,in,MAXLINE);
		if(n == 0){
			return;
		}else if(n < 0){
			err_abort("Fehler beim Lesen des Sockets!");
		}
		sprintf(out, "Echo: %s", in);

		// Daten schreiben
		if(write(sockfd, out, n+6) != n+6){
			err_abort("Fehler beim Schreiben des Sockets!");
		}
	}
}


/*
Ausgabe von Fehlermeldungen
*/
void err_abort(char *str){
	fprintf(stderr," TCP Echo-Server: %s\n",str);
	fflush(stdout);
	fflush(stderr);
	exit(1);
}
