SELECT EMPNO, ENAME, JOB, SAL,
        DECODE(JOB,
                'MANAGER', SAL*1.1,
                'SALESMAN', SAL*1.05,
                'ANALYST', SAL,
                SAL*1.03) AS UPSAL
FROM EMP;

SELECT EMPNO, ENAME, JOB, SAL,
    CASE JOB
        WHEN 'MANAGER' THEN SAL*1.1
        WHEN 'SALESMAN' THEN SAL*1.05
        WHEN 'ANALYST' THEN SAL
        ELSE SAL*1.03
    END AS UPSAL
FROM EMP;

SELECT EMPNO, ENAME, COMM,
    CASE
        WHEN COMM IS NULL THEN '해당사항 없음'
        WHEN COMM = 0 THEN '수당없음'
        WHEN COMM > 0 THEN '수당 : ' || COMM
    END AS COMM_TEXT
FROM EMP;

-- 문제

SELECT EMPNO, ENAME, RPAD(SUBSTR(EMPNO, 1, 2), 4, '*') AS MASKING_EMPNO,
        RPAD(SUBSTR(ENAME, 1, 1), LENGTH(ENAME), '*') AS MASKING_ENAME
FROM EMP
WHERE LENGTH(ENAME)>=5
AND LENGTH(ENAME)<6;

SELECT EMPNO, ENAME, SAL, TRUNC(SAL/21.5, 2) AS DAY_PAY, 
    ROUND(SAL/21.5/8, 1) AS TIME_PAY
FROM EMP;

SELECT EMPNO, ENAME, HIREDATE,
    TO_CHAR(NEXT_DAY(ADD_MONTHS(HIREDATE, 3), '월요일'), 'YYYY-MM-DD') AS R_JOB,
        NVL(TO_CHAR(COMM), 'N/A') AS COMM
FROM EMP;

SELECT EMPNO, ENAME, MGR, 
    CASE
        WHEN MGR IS NULL THEN 0000
        WHEN SUBSTR(MGR, 1, 2) = 75 THEN 5555
        WHEN SUBSTR(MGR, 1, 2) = 76 THEN 6666
        WHEN SUBSTR(MGR, 1, 2) = 77 THEN 7777
        WHEN SUBSTR(MGR, 1, 2) = 78 THEN 8888
        ELSE MGR
    END
        AS CHG_MGF
FROM EMP