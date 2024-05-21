SELECT ENAME, UPPER(ENAME), LOWER(ENAME), INITCAP(ENAME)
    FROM EMP;
    
SELECT *
    FROM EMP
    WHERE UPPER(ENAME) = UPPER('scott');

SELECT *
    FROM EMP
    WHERE UPPER(ENAME) LIKE UPPER('%scott%');
    
SELECT ENAME, LENGTH(ENAME)
    FROM EMP;

SELECT ENAME, LENGTH(ENAME)
    FROM EMP
    WHERE LENGTH(ENAME) >= 5;
    
SELECT LENGTH('한글'), LENGTHB('한글')
    FROM DUAL;
    
SELECT JOB, SUBSTR(JOB, 1, 2), SUBSTR(JOB, 3, 2), SUBSTR(JOB, 5)
    FROM EMP;
    
SELECT JOB,
        SUBSTR(JOB, -LENGTH(JOB)),
        SUBSTR(JOB, -LENGTH(JOB), 2),
        SUBSTR(JOB, -3)
FROM EMP;

SELECT INSTR('HELLO, ORACLE!', 'L') AS INSTER_1,
        INSTR('HELLO, ORACLE!', 'L', 5) AS INSTER_2,
        INSTR('HELLO, ORACLE!', 'L', 2, 2) AS INSTER_3
FROM DUAL;

SELECT *
FROM EMP
WHERE INSTR(ENAME, 'S') > 0;

SELECT *
FROM EMP
WHERE ENAME LIKE UPPER('%s%');

SELECT '010-1234-5678' AS REPLACE_BEFORE,
        REPLACE('010-1234-5678', '-', ' ') AS REPLACE_1,
        REPLACE('010-1234-5678', '-') AS REPLACE_2
FROM DUAL;

SELECT 'Oracle',
        LPAD('Oracle', 10, '#') AS LPAD_1,
        RPAD('Oracle', 10, '*') AS RPAD_1,
        LPAD('Oracle', 10) AS LPAD_2,
        RPAD('Oracle', 10) AS RPAD_2
FROM DUAL;

SELECT
        RPAD('971225-', 14, '*') AS RPAD_JMNO,
        RPAD('010-1234-', 13, '*') AS RPAD_PHONE
FROM DUAL;

SELECT 
        CONCAT(EMPNO, ENAME),
        CONCAT(EMPNO, CONCAT(' : ', ENAME))
FROM EMP
WHERE LOWER(ENAME) = LOWER('scott');

SELECT
        EMPNO || ENAME,
        EMPNO || ' : ' || ENAME
FROM EMP
WHERE INITCAP(ENAME) = INITCAP('scott');

SELECT 
    '[' || TRIM(' _ _Oracle_ _ ') || ']' AS TRIM,
    '[' || TRIM(LEADING FROM ' _ _Oracle_ _ ') || ']' AS TRIM_LEADING,
    '[' || TRIM(TRAILING FROM ' _ _Oracle_ _ ') || ']' AS TRIM_TRAILING,
    '[' || TRIM(BOTH FROM ' _ _Oracle_ _ ') || ']' AS TRIM_BOTH
FROM DUAL;

SELECT
        '[' || TRIM('_' FROM '_ _Oracle_ _') || ']' AS TRIM,
        '[' || TRIM(LEADING '_' FROM '_ _Oracle_ _') || ']' AS TRIM_LEADING,
        '[' || TRIM(TRAILING '_' FROM '_ _Oracle_ _') || ']' AS TRIM_TRAILING,
        '[' || TRIM(BOTH '_' FROM '_ _Oracle_ _') || ']' AS TRIM_BOTH
FROM DUAL;

SELECT
        '[' || TRIM(' _Oracle_ ') || ']' AS TRIM,
        '[' || LTRIM(' _Oracle_ ') || ']' AS LTRIM,
        '[' || LTRIM('<_Oracle_>', '_<') || ']' AS LTRIM_2,
        '[' || RTRIM(' _Oracle_ ') || ']' AS RTRIM,
        '[' || RTRIM('<_Oracle_>', '>_') || ']' AS RTRIM_2
FROM DUAL;

