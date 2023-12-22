DO $$
    DECLARE
        start_time TIMESTAMP;
        result_record RECORD;
    BEGIN
        start_time := clock_timestamp();

        SELECT * into result_record  FROM jpaowner ;

        RAISE NOTICE 'Time taken: % milliseconds',
                EXTRACT(EPOCH FROM (clock_timestamp() - start_time)) * 1000;
    END $$;


DO $$
    DECLARE
        start_time TIMESTAMP;
        result_record RECORD;
    BEGIN
        start_time := clock_timestamp();

        SELECT * into result_record  FROM jpaowner WHERE name like 'Owner0';

        RAISE NOTICE 'Time taken: % milliseconds',
                EXTRACT(EPOCH FROM (clock_timestamp() - start_time)) * 1000;
    END $$;

DO $$
    DECLARE
        start_time TIMESTAMP;
        result_record RECORD;
    BEGIN
        start_time := clock_timestamp();

        SELECT name, mana into result_record  FROM jpaowner WHERE name LIKE 'Owner0';

        RAISE NOTICE 'Time taken: % milliseconds',
                EXTRACT(EPOCH FROM (clock_timestamp() - start_time)) * 1000;
    END $$;


DO $$
    DECLARE
        start_time TIMESTAMP;
        result_record RECORD;
    BEGIN
        start_time := clock_timestamp();

        SELECT name into result_record  FROM jpaowner WHERE name LIKE 'Owner0' ORDER BY mana;

        RAISE NOTICE 'Time taken: % milliseconds',
                EXTRACT(EPOCH FROM (clock_timestamp() - start_time)) * 1000;
    END $$;

DO $$
    DECLARE
        start_time TIMESTAMP;
    BEGIN
        start_time := clock_timestamp();

        UPDATE jpaowner set name = 'Meistermagier' WHERE mana > 90;

        RAISE NOTICE 'Time taken: % milliseconds',
                EXTRACT(EPOCH FROM (clock_timestamp() - start_time)) * 1000;
    END $$;


DO $$
    DECLARE
        start_time TIMESTAMP;
    BEGIN
        start_time := clock_timestamp();

        DELETE FROM jpaowner_pet_list WHERE jpaowner_pet_list.jpaowner_jpaid IN (SELECT jpaid FROM jpaowner WHERE mana < 5);

        DELETE FROM jpaowner WHERE mana < 5;


        RAISE NOTICE 'Time taken: % milliseconds',
                EXTRACT(EPOCH FROM (clock_timestamp() - start_time)) * 1000;
    END $$;