// ===========================================================================
//  demo2_win.cpp   --   "Kiosk Notes"
//
//  A small interactive notes program, the kind a beginner might write. You
//  write a note, show it, delete it, and you can import an external record as
//  raw hex bytes.
//
//  Drive it yourself:
//      make demo2_win
//      setarch -R ./demo2_win
//
//  The program prints a leftover debug line at startup with the address of a
//  maintenance routine.
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

typedef void (*view_fn)();

struct Note {
    char    text[24];
    view_fn show;       // how to display this note
};

Note* cur = NULL;       // the current note

void show_plain() {
    if (cur) {
        printf("  Note: %s\n", cur->text);
    }
}

// A maintenance routine. It is NOT on the menu and nothing is supposed to
// call it during normal use.
void maintenance_mode() {
    printf("  >>> MAINTENANCE MODE <<<\n");
}

static void readline(const char* prompt, char* buf, size_t n) {
    printf("%s", prompt);
    fflush(stdout);
    if (!fgets(buf, (int)n, stdin)) { 
        buf[0] = '\0'; 
        return; 
    }
    buf[strcspn(buf, "\n")] = '\0';
}

void new_note() {
    cur = (Note*) malloc(sizeof(Note));
    readline("  Note text: ", cur->text, sizeof cur->text);
    cur->show = show_plain;           // a new note displays as plain text
    printf("  Saved.\n");
}

void show_note() {
    if (!cur) { 
        printf("  No note.\n"); 
        return; 
    }
    cur->show();                      // display this note its own way
}

void delete_note() {
    if (!cur) { 
        printf("  No note.\n"); 
        return; 
    }
    free(cur);                        // the beginner keeps using cur after this
    printf("  Deleted.\n");
}

// Import an external record, provided as raw hex, into a scratch buffer.
void import_record() {
    char hex[128];

    readline("  Paste record: ", hex, sizeof hex);
    unsigned char* rec = (unsigned char*) malloc(sizeof(Note));
    int n = 0;
    for (int i = 0; hex[i] && hex[i + 1] && n < (int)sizeof(Note); i += 2) {
        unsigned int byte;
        if (sscanf(hex + i, "%2x", &byte) != 1) {
            break; // ugg terrible coding
        }
        rec[n++] = (unsigned char) byte;
    }
    printf("  Imported %d bytes.\n", n);
}

int main() {
    char line[16];

    printf("=== Kiosk Notes ===\n");
    printf("  [debug] maintenance_mode at %p\n", (void*)&maintenance_mode);
    for (;;) {  // terrible programming practice
        printf("\n[1] New note  [2] Show note  [3] Delete note  [4] Import record  [5] Quit\n");
        readline("> ", line, sizeof line);
        switch (atoi(line)) {
            case 1: new_note();     break;
            case 2: show_note();    break;
            case 3: delete_note();  break;
            case 4: import_record(); break;
            case 5: printf("Bye.\n"); return 0; // ugg terrible practice
            default: printf("  (type 1-5)\n");
        }
    }
}
