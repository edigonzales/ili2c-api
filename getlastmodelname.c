#include <stdlib.h>
#include <stdio.h>

#include <libili2c.h>

int main(int argc, char **argv) {
    graal_isolate_t *isolate = NULL;
    graal_isolatethread_t *thread = NULL;

    if (graal_create_isolate(NULL, &isolate, &thread) != 0) {
        fprintf(stderr, "graal_create_isolate error\n");
        return 1;
    }

    char * fileName = argv[1];

    printf("Hallo Welt!\n");
    printf("%s\n", getLastModelName(thread, fileName));

    if (graal_detach_thread(thread) != 0) {
        fprintf(stderr, "graal_detach_thread error\n");
        return 1;
    }

    return 0;

}
