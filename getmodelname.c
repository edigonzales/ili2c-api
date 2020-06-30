#include <stdlib.h>
#include <stdio.h>

#include <libfoobar.h>

int main(int argc, char **argv) {
    graal_isolate_t *isolate = NULL;
    graal_isolatethread_t *thread = NULL;

    if (graal_create_isolate(NULL, &isolate, &thread) != 0) {
        fprintf(stderr, "graal_create_isolate error\n");
        return 1;
    }

    printf("Hallo Welt!\n");
    //printf("%s\n", getLastModelName(thread, "./SO_AGI_AV_GB_Administrative_Einteilungen_Publikation_20180822.ili"));
    getLastModelName(thread, "S");

    if (graal_detach_thread(thread) != 0) {
        fprintf(stderr, "graal_detach_thread error\n");
        return 1;
    }

    return 0;

}
