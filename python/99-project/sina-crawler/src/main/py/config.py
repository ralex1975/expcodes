# -*- coding: utf-8 -*-
__author__ = 'EXP (272629724@qq.com)'
__date__ = '2018-03-29 20:44'


from __project__ import BASR_DIR
import configparser


config = configparser.ConfigParser()
config.read(filenames=('%s%s' % (BASR_DIR, '/conf/url.ini')), encoding='utf-8')


DEFAULT_CHARSET = 'utf-8'   # 默认编码
HTTP_CHARSET = 'gbk'        # 新浪HTTP内容编码

SLEEP_TIME = 0.1    # 行为休眠间隔(s)
TIMEOUT = 10        # 请求超时(s)
BATCH_LIMT = 20     # 每次批量请求的数量限制
RETRY = 5           # 重试次数

ACCOUNT_PATH = '%s%s' % (BASR_DIR, '/conf/account.dat')    # 登陆信息的保存位置
VCODE_PATH = '%s%s' % (BASR_DIR, '/conf/vcode.jpg')        # 登陆验证码图片的存储位置
DATA_DIR = '%s%s' % (BASR_DIR, '/data/')                   # 下载相册/说说数据的存储目录

PRE_LOGIN_URL = config.get('lander', 'PRE_LOGIN_URL')   # 获取登陆前参数的URL
VCODE_URL = config.get('lander', 'VCODE_URL')           # 获取登陆验证码图片的URL
LOGIN_URL = config.get('lander', 'LOGIN_URL')           # 新浪微博登陆URL


def QZONE_HOMR_URL(QQ):
    '''
    获取QQ空间首页地址
    :param QQ: QQ号
    :return:
    '''
    return '%(QZONE_DOMAIN)s%(QQ)s' % { 'QZONE_DOMAIN' : 'xxx', 'QQ' : QQ }


def ALBUM_URL(QQ, AID):
    '''
    获取QQ相册地址
    :param QQ: QQ号
    :param AID: 相册ID
    :return:
    '''
    return '%(QZONE_HOMR_URL)s/photo/%(AID)s' % { 'QZONE_HOMR_URL' : QZONE_HOMR_URL(QQ), 'AID' : AID }


