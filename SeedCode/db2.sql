-- 단일 행 서브쿼리
-- 전사원의 평균급여보다 많이 받는 사원 조회

select 	ename,sal
from	emp
where	sal > (select avg(sal) from emp);

-- 다중행 서브쿼리
-- 상사인 직원 조회
-- 상사의 목록을 구하고 사원마다 상사의 목록에 일치하는지 확인
select distinct mgr from emp where mgr is not null;
select 	*
from	emp
where	empno in (select distinct mgr from emp where mgr is not null);

--  pairwise 서브쿼리
-- 자신의 부서에서 최대급여를 받는 사원
-- 모든 부서별 최대급여를 조회 (10,5000) ....
select 	deptno, sal,ename
from	emp
where	(ifnull(deptno,0),sal)	in(
    select 	ifnull(deptno,0) ,max(sal)
    from	emp
    group by deptno
);
-- 위와 결과 다름(부서별 최대급여 리스트의 급여와 같은 급여를 받는 사원)
-- select 	deptno, sal,ename
-- from	emp
-- where	sal	in(
-- 							select 	max(sal)
-- 							from	emp
-- 							group by deptno
-- 						);

-- 상관관계(상호연관) 서브쿼리
-- 위와 같은 결과
-- 자신의 부서에서 최대급여를 받는 사원
select 	m.deptno, m.sal,m.ename
from	emp m
where	m.sal = (
    select  max(s.sal)
    from	emp s
    where 	s.deptno = m.deptno
);

select 	m.ename,m.deptno,m.sal, (
    select  avg(s.sal)
    from	emp s
    where 	s.deptno = m.deptno
) avgsal
from	emp m;

-- 상사인 직원 조회
-- 세미조인
select 	m.empno, m.ename
from	emp m
where	exists (
                 select 	1	-- 조회하는 데이터는 중요하지 않음
                 from	emp e
                 where	m.empno = e.mgr
             );

-- 상사가 아닌 평사원 조회
select 	m.empno, m.ename
from	emp m
where	not exists (
        select 	1	-- 조회하는 데이터는 중요하지 않음
        from	emp e
        where	m.empno = e.mgr
    );


-- 상위 레코드 top 5
select 	empno, sal
from		emp
order by 	sal desc
    limit	5;

-- 상위  top 6 - 10
select 	empno, sal
from		emp
order by 	sal desc
    limit	5,5;




