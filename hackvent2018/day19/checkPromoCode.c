int _checkPromoCode(int par0) {
    unsigned int* r63 = __env.STACKTOP;

    if(__env.STACKTOP + &gvar_1C >= __env.STACK_MAX) {
        abortStackOverflow(112);
    }

    unsigned int* r11 = r63 + &gvar_8;
    unsigned int* r22 = r63 + 23;
    unsigned int* r33 = r63;
    int r0 = par0;
    *r11 = gvar_400;
    *((unsigned int)(((int)gvar_8) + ((int)r11))) = gvar_408;
    *((unsigned int)(((int)gvar_10) + ((int)r11))) = gvar_410;
    *((unsigned int)(((int)gvar_18) + ((int)r11))) = gvar_418;
    *((unsigned int)(((int)gvar_1C) + ((int)r11))) = gvar_41C;
    *r22 = 0L;
    *((unsigned int)(((int)gvar_8) + ((int)r22))) = 0;
    *((unsigned int)(((int)gvar_C) + ((int)r22))) = 0;
    *(((unsigned int)(((int)gvar_C) + ((int)r22))) + 2) = 0;
    *r63 = *((long long*)(&aHV18_TRYH_ARDE_[0]));
    *((unsigned int)(((int)gvar_8) + ((int)r63))) = *((long long*)(&aHV18_TRYH_ARDE_[&gvar_8]));
    *((unsigned int)(((int)gvar_10) + ((int)r63))) = *((long long*)(&aHV18_TRYH_ARDE_[&gvar_10]));
    *((unsigned int)(((int)gvar_18) + ((int)r63))) = *((int*)(&aHV18_TRYH_ARDE_[&gvar_18]));
    *((unsigned int)(((int)gvar_1C) + ((int)r63))) = *((short*)(&aHV18_TRYH_ARDE_[&gvar_1C]));
    unsigned int r44 = 0;
    int r55 = 165;
    int var50 = f32(par0);

    if(var50 == 15) {
        unsigned int r59 = 0;

        while(1) {
            unsigned int r5 = r59;
            int var61 = f32(r0);

            if(r59 >= var61) {
                break;
            }
            else {
                unsigned int* var73 = r0 + r59;
                int r60 = ((int)(*var73)) * r55 + 1337;
                unsigned int* var91 = r0 + r59;
                r44 = ((int)(*var91)) + r44 + r60;
                int r61 = r60 % 255;
                *((unsigned int*)(((int)r22) + r59)) = (unsigned char)r61;
                r55 = r61;
                ++r59;
            }
        }

        if(r44 != 217675) {
            __env.STACKTOP = r63;
            return r33;
        }
        else {

            for(int r1 = 0; r1 < 30; ++r1) {
                unsigned int* var142 = (unsigned int*)(r1 + ((int)r11));
                unsigned int* var153 = (unsigned int*)(r1 % 15 + ((int)r22));
                *((unsigned int*)(r1 + ((int)r33))) = (unsigned char)(((int)(*var142)) ^ ((int)(*var153)));
            }
        }
    }

    __env.STACKTOP = r63;
    return r33;
}
