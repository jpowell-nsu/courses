// ===========================================================================
//  demo1_isadmin.cpp   --   "Residence Hall Door Kiosk"
//
//  A small interactive login kiosk, the kind a beginner might write. People
//  badge in with a username and password, check their access level, and badge
//  out so the next person can use it.
//
//  Drive it yourself:
//      make demo1_isadmin
//      setarch -R ./demo1_isadmin
//
//  Note: Some of the code here is bad coding practice (like the returns 
//      and infinite loops. This resulted from some AI generated components
//      that I did not want to spend time fixing. Those are cosmetic and do
//      affect the real issue. I did use AI to help me set up the error--I am
//      not good at purposely setting up errors
// ===========================================================================

#include <cstdio>
#include <cstdlib>
#include <cstring>

struct Session {
    char name[24];
    int  isAdmin;     // 0 = resident, 1 = staff (can open the server room)
};

Session* cur = NULL;  // whoever is currently badged in

static void readline(const char* prompt, char* buf, size_t n) {
    printf("%s", prompt);
    fflush(stdout);
    if (!fgets(buf, (int)n, stdin)) { 
        buf[0] = '\0'; 
        return; 
    }
    buf[strcspn(buf, "\n")] = '\0';
}

void badge_in() {
    char user[24], pass[24];
    readline("  Username: ", user, sizeof user);
    readline("  Password: ", pass, sizeof pass);

    cur = (Session*) malloc(sizeof(Session));
    strncpy(cur->name, user, sizeof cur->name - 1);
    cur->name[sizeof cur->name - 1] = '\0';

    if (strcmp(user, "ra") == 0 && strcmp(pass, "letmein") == 0) {
        cur->isAdmin = 1;   // staff get elevated access
    }

    printf("  Welcome, %s.\n", cur->name);
}

void check_access() {
    if (!cur) { 
        printf("  Nobody is badged in.\n"); 
        return; 
    }
    
    printf("  User: %s | access level: %s\n",
           cur->name, cur->isAdmin ? "STAFF" : "resident");

    if (cur->isAdmin) {
        printf("  >>> SERVER ROOM DOOR UNLOCKED <<<\n");
    }
}

void badge_out() {
    if (!cur) { 
        printf("  Nobody is badged in.\n"); 
        return; 
    }
    printf("  Goodbye, %s.\n", cur->name);
    free(cur);  // ready for the next person to badge in
}

int main() {
    char line[16];

    printf("=== Residence Hall Door Kiosk ===\n");
    for (;;) {  // terrible programming practice
        printf("\n[1] Badge in   [2] Check access   [3] Badge out   [4] Quit\n");
        readline("> ", line, sizeof line);
        
        switch (atoi(line)) {
            case 1: badge_in();     break;
            case 2: check_access(); break;
            case 3: badge_out();    break;
            case 4: printf("Bye.\n"); return 0; // ugg terrible practice
            default: printf("  (type 1, 2, 3, or 4)\n");
        }
    }
}
