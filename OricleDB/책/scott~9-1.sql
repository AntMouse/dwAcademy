SELECT SAL
FROM EMP
WHERE ENAME = 'JONES';

SELECT *
FROM EMP
WHERE SAL > 2975;

SELECT *
FROM EMP
WHERE SAL > (SELECT SAL
            FROM EMP
            WHERE ENAME = 'JONES');
            
-- ¹®Á¦
SELECT *
FROM EMP
WHERE COMM > (SELECT COMM
                FROM EMP
                WHERE ENAME = 'ALLEN');
                
