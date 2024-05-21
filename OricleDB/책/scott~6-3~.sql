SELECT
        ROUND(1234.5678) AS ROUND,
        ROUND(1234.5678, 0) AS ROUND_0,
        ROUND(1234.5678, 1) AS ROUND_1,
        ROUND(1234.5678, 2) AS ROUND_2,
        ROUND(1234.5678, -1) AS ROUND_MINUS1,
        ROUND(1234.5678, -2) AS ROUND_MINUS2
FROM DUAL;

SELECT
        TRUNC(1234.5678) AS TRUNC,
        TRUNC(1234.5678, 0) AS TRUNC_0,
        TRUNC(1234.5678, 1) AS TRUNC_1,
        TRUNC(1234.5678, 2) AS TRUNC_2,
        TRUNC(1234.5678, -1) AS TRUNC_MINUS1,
        TRUNC(1234.5678, -2) AS TRUNC_MINUS2
FROM DUAL;

SELECT
        CEIL(3.14),
        FLOOR(3.14),
        CEIL(-3.14),
        FLOOR(-3.14)
FROM DUAL;

SELECT
        MOD(15, 6),
        MOD(10, 2),
        MOD(11, 2)
FROM DUAL;

SELECT
        SYSDATE AS NOW,
        SYSDATE-1 AS YESTERDAY,
        SYSDATE+1 AS TOMORROW
FROM DUAL;

SELECT
        ADD_MONTHS(SYSDATE, 3)
FROM DUAL;

SELECT
        EMPNO, ENAME, HIREDATE, ADD_MONTHS(HIREDATE, 120) AS WORK10YEAR
FROM EMP;

SELECT
        EMPNO, ENAME, SYSDATE
FROM EMP
WHERE ADD_MONTHS(HIREDATE, 500) > SYSDATE;